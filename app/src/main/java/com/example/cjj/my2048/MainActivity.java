package com.example.cjj.my2048;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static  MainActivity mainActivity;
    private TextView tvScoreNum;
    private  int scoreNum = 0;
    private GameView gameView;

    public MainActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         gameView= (GameView) findViewById(R.id.gl_gameview);

        final Button ngBtn= (Button) findViewById(R.id.bt_newGame);
        ngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.restartGame();
            }
        });

        tvScoreNum= (TextView) findViewById(R.id.tv_scoreNumber);

    }

    /**
     * 增加分数
     * @param score
     */
    public void addScoreNum(int score) {
         scoreNum +=score;
         tvScoreNum.setText(scoreNum+"");
    }

    /**
     * 分数清零
     */
    public void clearScore(){
        scoreNum=0;
        tvScoreNum.setText(scoreNum+"");
    }


    /**
     * 获取分数
     * @return
     */
    public int getScoreNum() {
        return scoreNum;
    }



    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
