package com.example.cjj.my2048;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by CJJ on 2016/7/8.
 */
public class Card extends FrameLayout {
    private TextView tvNumber;
    private int num=0;
    private ScaleAnimation animation1,animation2;

    public Card(Context context) {
        super(context);
        tvNumber=new TextView(getContext());
        tvNumber.setTextSize(32);
        tvNumber.setGravity(Gravity.CENTER);
        tvNumber.setBackgroundColor(0xffEEE4DA);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,10,0,0);
        addView(tvNumber,lp);

        animation1 =new ScaleAnimation(0.9f, 1.2f, 0.9f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation1.setDuration(100);

        animation2=new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation2.setDuration(300);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num,int animFlag) {
        this.num = num;
        if(num<=0){
            tvNumber.setText("");
        }else{
            tvNumber.setText(num+"");
        }


        if(animFlag==1){
            //执行合并的动画
            tvNumber.startAnimation(animation1);
        }else if(animFlag==2){
            //数字出现动画
            tvNumber.startAnimation(animation2);
        }

        //不同的数字，设置不同的背景
        switch (num){
            case 0:
                tvNumber.setBackgroundColor(0xffEEE4DA);
                break;
            case 2:
                tvNumber.setBackgroundColor(0xffFCC39D);
                break;
            case 4:
                tvNumber.setBackgroundColor(0xffFA9A5B);
                break;
            case 8:

                tvNumber.setBackgroundColor(0xffF87219);
                break;
            case 16:
                tvNumber.setBackgroundColor(0xffD65A08);
                break;
            case 32:
                tvNumber.setBackgroundColor(0xfff67c5f);
                break;
            case 64:
                tvNumber.setBackgroundColor(0xffFF5C33);
                break;
            case 128:
                tvNumber.setBackgroundColor(0xffDD2C00);
                break;

            default:
                tvNumber.setBackgroundColor(0xffDD2C00);
                break;
        }

    }


    /**
     * 判断2个card的数字是否相等
     * @param card
     * @return
     */
    public boolean equals(Card card) {
        return getNum()==card.getNum();
    }
}
