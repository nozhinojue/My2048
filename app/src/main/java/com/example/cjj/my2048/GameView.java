package com.example.cjj.my2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by CJJ on 2016/7/8.
 */
public class GameView extends GridLayout {
    public static final String TAG=GameView.class.getSimpleName();

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
                            if(offsetX<-5){
                                //向左滑动
                                swipLeft();
                            }else if(offsetX > 5){
                                //向右滑动
                                swipRight();
                            }
                        }else{
                            if(offsetY <-5){
                                //向上滑动
                                swipUp();
                            }else if(offsetY > 5){
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

        //根据屏幕的宽高，计算card的宽高。
        int cardWidth=(Math.min(w,h)-10) / 4;
        addCards(cardWidth,cardWidth);
    }


    //加载card到Gameview中
    private void addCards(int width,int height) {
        Card card;
        for (int y=0; y<4 ; y++){
            for (int x=0; x<4 ; x++){
                card= new Card(getContext());
                card.setNum(2);
                addView(card,width,height); //添加到gameview中
            }
        }
    }

    //向左滑动
    private void swipLeft(){

    }

    //向右滑动
    private void swipRight(){

    }

    //向上滑动
    private void swipUp(){

    }

    //向下滑动
    private void swipDown(){

    }

















}
