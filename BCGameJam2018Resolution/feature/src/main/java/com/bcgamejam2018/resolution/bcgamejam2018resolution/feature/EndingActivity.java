package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

import java.util.ArrayList;

public class EndingActivity extends AppCompatActivity {

    ImageButton btn;
    int intelligence = 5;
    int wealth = 5;
    int relationship = 5;
    int health = 5;
    int highest = 0;
    int hightestStat = 0;
    int[] statArray = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        Intent intent = getIntent();
        intelligence = intent.getIntExtra("intelligence", 5);
        wealth = intent.getIntExtra("wealth", 5);
        relationship = intent.getIntExtra("relationship", 5);
        health = intent.getIntExtra("health", 5);
        statArray[0] = intelligence;
        statArray[1] = wealth;
        statArray[2] = relationship;
        statArray[3] = health;
        btn = findViewById(R.id.resultImageButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //fail ending
        if(intelligence < 75 && wealth < 75 && relationship < 75 && health < 75){
            btn.setBackgroundResource(R.drawable.fail_ending);
        }else{
            for(int i = 1; i < 4; i++){
                if(statArray[i] > statArray[hightestStat]){
                    hightestStat = i;
                }
            }
            switch (hightestStat){
                case 0 :
                    btn.setBackgroundResource(R.drawable.intelligence_ending);
                    break;
                case 1 :
                    btn.setBackgroundResource(R.drawable.wealth_ending);
                    break;
                case 2 :
                    btn.setBackgroundResource(R.drawable.relationship_ending);
                    break;
                case 3 :
                    btn.setBackgroundResource(R.drawable.health_ending);
                    break;
            }

        }

    }
}
