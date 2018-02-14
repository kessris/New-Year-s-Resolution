package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class ExerciseActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    TextView stepstext;
    TextView goalstepstext;
    TextView healthScore;
    int gameLevel = 1;
    int goalsteps = 50*gameLevel;
    float steps = 0;
    TextView level;
    boolean activityRunning = false;
    ImageButton homeButton;

    private static boolean DEMO = true;

    public void returnHome(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        stepstext = (TextView) findViewById(R.id.stepstext);
        goalstepstext = (TextView) findViewById(R.id.goalstepstext);
        level = (TextView) findViewById(R.id.level);
        healthScore = (TextView) findViewById(R.id.healthScore);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        goalstepstext.setText(String.valueOf(goalsteps));


        homeButton = (ImageButton) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                steps = 0;
                if(DEMO) {
                    HomeActivity.setHealthStat(50);
                    steps = 50;
                }
                activityRunning = false;
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        steps = 0;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        steps = sensorEvent.values[0];
        if (activityRunning) {
            stepstext.setText(String.valueOf(sensorEvent.values[0]));
            healthScore.setText("Health Score: " + String.valueOf(HomeActivity.health));
            level.setText("Level " + String.valueOf(gameLevel));
            if (steps == goalsteps) {
                HomeActivity.setHealthStat(1);
                if ((HomeActivity.health % 10) == 0 && steps > 1) {
                    gameLevel = this.gameLevel +1;
                }
                Toast.makeText(this, "Goal accomplished!", Toast.LENGTH_SHORT).show();
                steps = 0;
                activityRunning = false;
                // HomeActivity.gethealthPoints.()
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}