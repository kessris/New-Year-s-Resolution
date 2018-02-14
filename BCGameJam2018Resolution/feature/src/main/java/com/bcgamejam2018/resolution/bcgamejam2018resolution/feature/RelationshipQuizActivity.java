package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RelationshipQuizActivity extends AppCompatActivity {


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED){
            this.finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship_quiz);

        findViewById(R.id.discoveryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subTopicChosen("discovery");
            }
        });
        findViewById(R.id.romanceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subTopicChosen("romance");
            }
        });
        findViewById(R.id.breakupButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subTopicChosen("breakup");
            }
        });
    }

    private void subTopicChosen(String subTopic) {
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        intent.putExtra("quiztype", "relationship");
        intent.putExtra("quiztopic", subTopic);
        startActivityForResult(intent, 100);
    }

    public void goBack(View view){
        this.finish();
    }
}
