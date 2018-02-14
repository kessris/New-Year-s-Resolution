package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    int score = 0;
    TextView scoreTextView;
    List<Quiz> quizzes;
    int currentQuizIndex = 0;
    int currentCorrectIndex;

    TextView titleTextView;
    Button choice1Button;
    Button choice2Button;
    Button choice3Button;
    Button choice4Button;

    Button[] choiceButtons = new Button[4];

    Button solutionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        scoreTextView = findViewById(R.id.scoreTextView);
        titleTextView = findViewById(R.id.questionTextView);
        solutionButton = findViewById(R.id.solutionButton);
        choice1Button = findViewById(R.id.choice1Button);
        choice1Button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                chooseAnswer(0);
                solutionButton.setVisibility(View.VISIBLE);
            }
        });
        choice2Button = findViewById(R.id.choice2Button);
        choice2Button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                chooseAnswer(1);
                solutionButton.setVisibility(View.VISIBLE);
            }
        });
        choice3Button = findViewById(R.id.choice3Button);
        choice3Button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                chooseAnswer(2);
                solutionButton.setVisibility(View.VISIBLE);
            }
        });
        choice4Button = findViewById(R.id.choice4Button);
        choice4Button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                chooseAnswer(3);
                solutionButton.setVisibility(View.VISIBLE);
            }
        });

        choiceButtons[0] = choice1Button;
        choiceButtons[1] = choice2Button;
        choiceButtons[2] = choice3Button;
        choiceButtons[3] = choice4Button;

        solutionButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                generateQuiz(quizzes, ++currentQuizIndex);
                solutionButton.setVisibility(View.INVISIBLE);

            }
        });

        // 1. Get type of the quiz
        // 2. Get topic of the quiz
        // 3. Get level of the quiz
        Intent intent = getIntent();
        String quizType = intent.getStringExtra("quiztype"); quizType  = quizType == null ? "relationship" : quizType;
        String quizTopic = intent.getStringExtra("quiztopic");quizTopic  = quizTopic == null ? "discovery" : quizTopic;
        Integer quizLevel = intent.getIntExtra("quizlevel", 0);

        // 4. Load json and parse it into quiz features/src/main/assets/quiz.json
        String quizJSONString = Util.loadJSONFromAsset(this.getApplicationContext(), "quiz.json");
        JSONObject quizObj = Util.parseJSONString(quizJSONString);

        try {

            JSONObject quizTypeObj = quizObj.getJSONObject(quizType);
            JSONArray quizzes = null;
            switch(quizType) {
                case "relationship":
                    JSONObject quizTopicObj = quizTypeObj.getJSONObject(quizTopic);
                    quizzes = quizTopicObj.getJSONArray("quizzes");
                    break;
                default:
                    break;
            }

            this.quizzes = new GsonBuilder().create().fromJson(quizzes.toString(), new TypeToken<ArrayList<Quiz>>(){}.getType());
            generateQuiz(this.quizzes, 0);
            // 4. Based on these values, create a quiz and calculate the score.


        } catch(Exception e) {
            Log.e("QuizActivity", e.getMessage());
        }
    }

    public void goBack(View view){
        this.finish();
    }

    private void generateQuiz(List<Quiz> quizzes, int quizIndex) {
        for(Button b: choiceButtons) {
            b.setBackgroundColor(Color.LTGRAY);
        }
        if(quizIndex >= quizzes.size()) {
            // No more quizes, go back to the main
            Util.showToast(getApplicationContext(), "Done!! you got " + this.score);
            HomeActivity.setRelStat(score);
            this.finish();
        } else {
            Quiz quiz = quizzes.get(quizIndex);
            titleTextView.setText(quiz.information);
            currentCorrectIndex = quiz.correctIndex;
            choice1Button.setText(quiz.options[0]);
            choice2Button.setText(quiz.options[1]);
            choice3Button.setText(quiz.options[2]);
            choice4Button.setText(quiz.options[3]);
            solutionButton.setText(quiz.solution);

        }
    }

    private void chooseAnswer(int chosenIndex) {

        choiceButtons[chosenIndex].setBackgroundColor(Color.rgb(255, 80, 80));
        choiceButtons[currentCorrectIndex].setBackgroundColor(Color.rgb(102, 255, 102));


        if(chosenIndex == currentCorrectIndex) {
            setScore(this.score + 5);
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
        } else {
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.incorrect);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
        }
       // generateQuiz(this.quizzes, ++this.currentQuizIndex);
    }

    private void setScore(int score) {
        this.score = score;
        scoreTextView.setText("Score: " + score);
    }
}
