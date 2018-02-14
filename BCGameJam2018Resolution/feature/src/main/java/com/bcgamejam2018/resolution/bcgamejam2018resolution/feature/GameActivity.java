package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);
        ImageView character = findViewById(R.id.character);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                if(character.getY() == 80){
                    character.setY(400);
                }else{
                    character.setY(80);
                }
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public void update(){
        ImageView object = findViewById(R.id.object);
        object.setX(object.getX() - 20);
    }

    public void gameEnd(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewById(R.id.character).setY(80);
        findViewById(R.id.object).setX(800);
        new CountDownTimer(30000, 30) {
            public void onTick(long millisUntilFinished) {
                update();
            }

            public void onFinish() {
                gameEnd();
            }
        }.start();
    }
}
