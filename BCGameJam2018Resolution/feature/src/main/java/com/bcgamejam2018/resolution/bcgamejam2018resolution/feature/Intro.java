package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Intro extends AppCompatActivity {

    ImageButton first;
    ImageButton second;
    ImageButton rules;
    ImageButton skip;

    public void skip(View view){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void setIntro2Visible(View view){
        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    public void setRuleVisible(View view){
        second.setVisibility(View.INVISIBLE);
        skip.setVisibility(View.INVISIBLE);
        rules.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        first = findViewById(R.id.intro1);
        second = findViewById(R.id.intro2);
        rules = findViewById(R.id.rules);
        skip = findViewById(R.id.skip);
    }
}
