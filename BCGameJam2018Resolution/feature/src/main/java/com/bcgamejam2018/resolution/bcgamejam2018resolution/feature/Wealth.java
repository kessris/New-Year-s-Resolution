package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class Wealth extends AppCompatActivity {
    static boolean beg=false;
    TextView currPrice;
    TextView score;
    SeekBar seekBar;
    Button trybutton;
    ImageButton home;
    CountDownTimer countDownTimer;
    Random rand = new Random();
    int actualScore = 0;
    int tries = 3;
    int a;
    TextView stage;
    static int lv=1;
    Button result;
    Button instrc;



    public void goaway(View v){
        instrc.setVisibility(View.INVISIBLE);
    }
    public void returnHome(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }


    public void result(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
    public void onClickTryButton(View view){
        actualScore = actualScore + seekBar.getProgress();
        score.setText("Current money earned: " + actualScore + " Millions");
        tries--;
        if (tries == 0) {
            result.setVisibility(View.VISIBLE);
            result.setText("YOU EARNED $ " + actualScore + " Millions !");
            HomeActivity.setWealthStat(actualScore/13);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wealth);
        instrc = findViewById(R.id.instruction);
        lv = 1+ HomeActivity.getWealth()/20;
if(!beg) {
    instrc.setVisibility(View.VISIBLE);
    beg=true;
}
        stage = findViewById(R.id.lv);
        stage.setText("Level " + lv);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setProgress(0);
        currPrice = findViewById(R.id.stockPrice);
        result = findViewById(R.id.resultButton);
        trybutton = findViewById(R.id.tryButton);
        score = findViewById(R.id.numbersPicked);
        home = findViewById(R.id.home);
        seekBar.setEnabled(true);

        int temp = Math.max(600 - (lv*100), 100);
        countDownTimer = new CountDownTimer(60000, temp) {
            @Override
            public void onTick(long millisUntilFinished) {
                a = rand.nextInt(100); //generate random num between 0~100
                seekBar.setProgress(a);
            }
            @Override
            public void onFinish() {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        }.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currPrice.setText(i + " Millions");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
}
