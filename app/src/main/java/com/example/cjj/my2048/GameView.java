package com.example.cjj.my2048;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJJ on 2016/7/8.
 */
public class GameView extends GridLayout {
    public static final String TAG=GameView.class.getSimpleName();
    private Card[][] cardsMap=new  Card[4][4];   //记录所有的card
    private List<Point> emptyPoints= new ArrayList<>();  //记录空card值为空的位置。

    public GameView(Context context) {
        super(context);

        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameView();
    }

    //初始化
    private void initGameView(){
        setColumnCount(4);//设置列数
        setBackgroundColor(0xffB8AF9E);//设置背景色
        Log.i(TAG,"宽度为："+getWidth()) ;

        setOnTouchListener(new View.OnTouchListener() {

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //手指按下时
                        startX=motionEvent.getX();
                        startY=motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //手指弹起时
                        offsetX=motionEvent.getX()-startX;
                        offsetY=motionEvent.getY()-startY;

                        if(Math.abs(offsetX) > Math.abs(offsetY)){
                            if(offsetX<-10){
                                //向左滑动
                                swipLeft();
                            }else if(offsetX > 10){
                                //向右滑动
                                swipRight();
                            }
                        }else{
                            if(offsetY <-10){
                                //向上滑动
                                swipUp();
                            }else if(offsetY > 10){
                                //向下滑动
                               swipDown();
                            }
                        }

                        break;
                }

                return true;
            }
        });
    }



    //大小改变时，初始化时执行一次，因为设置了只能竖屏.
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i(TAG,"调用onSizeChanged");

        //根据屏幕的宽高，计算card的宽高。
        int cardWidth=(Math.min(w,h)-10) / 4;
        addCards(cardWidth,cardWidth);

    }

    //初始化加载card到Gameview中
    private void addCards(int width,int height) {

        Card card;
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                card= new Card(getContext());
                addView(card,width,height); //添加到gameview中

                cardsMap[x][y]=card;
            }
        }

        startGame();
    }

    //开始游戏
    public void startGame(){
        //初始化所有cards值为空。
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                cardsMap[x][y].setNum(0,0);
            }
        }

        addRandomCard();
        addRandomCard();
    }

    public void restartGame(){
        //清空分数
        MainActivity.getMainActivity().clearScore();

        startGame();
    }

    //空白位置添加随机card
    private void addRandomCard(){
        emptyPoints.clear();

        //循环遍历所有card的，获取所有空值card
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }

        if(emptyPoints.size()!=0){
            Point p= emptyPoints.get( (int)(Math.random()*emptyPoints.size()) );  //随机获取一个空点
            cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4,2);  //设置一个2或4的值给card，2或4的比例为9:1
        }

    }

    //向左滑动
    private void swipLeft(){
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                for(int x1=x+1; x1<4; x1++){
                    if(cardsMap[x1][y].getNum()>0){

                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum(),0);
                            cardsMap[x1][y].setNum(0,0);

                            x--;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2,1);
                            cardsMap[x1][y].setNum(0,0);
                            MainActivity.getMainActivity().addScoreNum(cardsMap[x][y].getNum());
                        }
                        break;

                    }

                }
            }
        }

        checkGameOver();
        addRandomCard();
    }

    //向右滑动
    private void swipRight(){
        for (int y=0; y<4 ; y++){
            for (int x=3; x>=0 ; x--){
                for(int x1=x-1; x1>=0; x1--){
                    if(cardsMap[x1][y].getNum()>0){

                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum(),0);
                            cardsMap[x1][y].setNum(0,0);

                            x++;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2,1);
                            cardsMap[x1][y].setNum(0,0);
                            MainActivity.getMainActivity().addScoreNum(cardsMap[x][y].getNum());

                        }
                        break;

                    }

                }
            }
        }

        checkGameOver();
        addRandomCard();
    }

    //向上滑动
    private void swipUp(){
        for (int x=0; x<4 ; x++){
            for (int y=0; y<4 ; y++){
                for(int y1=y+1; y1<4; y1++){
                    if(cardsMap[x][y1].getNum()>0){

                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum(),0);
                            cardsMap[x][y1].setNum(0,0);

                            y--;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2,1);
                            cardsMap[x][y1].setNum(0,0);
                            MainActivity.getMainActivity().addScoreNum(cardsMap[x][y].getNum());

                        }
                        break;

                    }

                }
            }
        }

        checkGameOver();
        addRandomCard();
    }

    //向下滑动
    private void swipDown(){
        for (int x=0; x<4 ; x++){
            for (int y=3; y>=0 ; y--){
                for(int y1=y-1; y1>=0; y1--){
                    if(cardsMap[x][y1].getNum()>0){

                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum(),0);
                            cardsMap[x][y1].setNum(0,0);

                            y++;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2,1);
                            cardsMap[x][y1].setNum(0,0);
                            MainActivity.getMainActivity().addScoreNum(cardsMap[x][y].getNum());

                        }
                        break;

                    }

                }
            }
        }

        checkGameOver();
        addRandomCard();
    }

    //检查游戏结束
    //条件是：没有空的card，或者 每个card的四个方向上不存在相同值得card
    private void checkGameOver(){
        boolean gameover=true;

        all:
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                if( cardsMap[x][y].getNum()<=0 ||
                        ( x>0 && cardsMap[x][y].equals(cardsMap[x-1][y])) ||
                        ( x<3 && cardsMap[x][y].equals(cardsMap[x+1][y])) ||
                        ( y>0 && cardsMap[x][y].equals(cardsMap[x][y-1])) ||
                        ( y<3 && cardsMap[x][y].equals(cardsMap[x][y+1]))  ){

                    gameover=false;
                    break all;
                }
            }
        }

        if(gameover){
            new AlertDialog.Builder(getContext())
                    .setTitle("提示")
                    .setMessage("游戏结束！")
                    .setCancelable(false)
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .show();
        }

    }










}
