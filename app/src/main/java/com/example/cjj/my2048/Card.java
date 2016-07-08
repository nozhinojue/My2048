package com.example.cjj.my2048;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by CJJ on 2016/7/8.
 */
public class Card extends FrameLayout {
    private TextView tvNumber;
    private int num=0;

    public Card(Context context) {
        super(context);
        tvNumber=new TextView(getContext());
        tvNumber.setTextSize(32);
        tvNumber.setGravity(Gravity.CENTER);
        tvNumber.setBackgroundColor(0xffEEE4DA);

        
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,10,0,0);
        addView(tvNumber,lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        tvNumber.setText(num+"");
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
