package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView monthsLeft;
    ImageButton intelButton;
    ImageButton wealthButton;
    ImageButton healthButton;
    ImageButton relButton;
    TextView calendar;
    int month = 12;
    List<String> monthList = new ArrayList<>(Arrays.asList("Jan", "Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"));
    // STATS
    static int intelligence = 5;
    static int wealth = 5;
    static int relationship= 5;
    static int health = 5;
    ImageView i;
    ImageView w;
    ImageView r;
    ImageView h;

    MediaPlayer mediaPlayer;

    static void setIntStat(int i){
        intelligence += i;
    }
    static void setHealthStat(int i){
        health += i;
    }
    static void setRelStat(int i){
        relationship += i;
    }
    static void setWealthStat(int i){
        wealth += i;
    }
    static int getWealth(){
        return wealth;
    }

    public void updateMonth(){
        if (month <= 0) {
            ending();
            return;
        }
        monthsLeft.setText("There's "+ month+" months left!!");
        int i = (month*(-1))+12;
        calendar.setText(monthList.get(i));

        android.view.ViewGroup.LayoutParams layoutParams = h.getLayoutParams();
        layoutParams.width = (int) (health * Resources.getSystem().getDisplayMetrics().density);
        layoutParams.height = (int) (10 * Resources.getSystem().getDisplayMetrics().density);
        h.setLayoutParams(layoutParams);

        layoutParams = r.getLayoutParams();
        layoutParams.width = (int) (relationship * Resources.getSystem().getDisplayMetrics().density);
        layoutParams.height = (int) (10 * Resources.getSystem().getDisplayMetrics().density);
        r.setLayoutParams(layoutParams);

        layoutParams = w.getLayoutParams();
        layoutParams.width = (int) (wealth * Resources.getSystem().getDisplayMetrics().density);
        layoutParams.height = (int) (10 * Resources.getSystem().getDisplayMetrics().density);
        w.setLayoutParams(layoutParams);

        layoutParams = this.i.getLayoutParams();
        layoutParams.width = (int) (intelligence * Resources.getSystem().getDisplayMetrics().density);
        layoutParams.height = (int) (10 * Resources.getSystem().getDisplayMetrics().density);
        this.i.setLayoutParams(layoutParams);
    }

    /////////////EDIT ENDING!!!!////////
    public void ending(){
        Toast.makeText(getApplicationContext(), "Game Over",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), EndingActivity.class);
        intent.putExtra("intelligence", intelligence);
        intent.putExtra("wealth", wealth);
        intent.putExtra("relationship", relationship);
        intent.putExtra("health", health);

        // Before opening end screen reset the score.
        intelligence = 5;
        wealth = 5;
        relationship = 5;
        health = 5;
        month = 12;

        startActivityForResult(intent, 100);
    }

    // Calls this when miniGame ends
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED){
            updateMonth();
        }

    }

    public void intelGame(View view){
        month -= 3;
        Intent intent = new Intent(getApplicationContext(), mathquiz.class);
        startActivityForResult(intent, 100);
    }

    public void wealthGame(View view){
        month -=3;
        Intent intent = new Intent(getApplicationContext(), Wealth.class);
        startActivityForResult(intent, 100);
    }

    public void relationshipGame(View view){
        month -=4;
        Intent intent = new Intent(getApplicationContext(), RelationshipQuizActivity.class);
        startActivityForResult(intent, 100);
    }

    public void healthGame(View view){
        month -=6;
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        monthsLeft = findViewById(R.id.sentence);
        intelButton = findViewById(R.id.intelButton);
        wealthButton = findViewById(R.id.wealthButton);
        healthButton = findViewById(R.id.healthButton);
        relButton = findViewById(R.id.relButton);
        calendar = findViewById(R.id.month);
        i = findViewById(R.id.intelStat2);
        w = findViewById(R.id.wealthStat2);
        r = findViewById(R.id.relStat2);
        h = findViewById(R.id.healthStat2);

    }
}