package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuizEndActivity extends AppCompatActivity {

    private String mResults [] = {
            "You are advised to stay home.",
            "You are advised to visit your local clinic to get tested for COVID-19.",
            "You are in healthy condition."
    };

    String userAnswer1;
    String userAnswer2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_quiz, container, false);
    }

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);

        Intent receivedIntent = getIntent();
        userAnswer1 = receivedIntent.getStringExtra("userAnswer1");
        userAnswer2 = receivedIntent.getStringExtra("userAnswer2");



        textView = findViewById(R.id.quizResults);
        //textView.setText(userAnswer1 + " " + userAnswer2);

        if (userAnswer1.equals("None of the above") && userAnswer2.equals("None of these symptoms")){
            textView.setText(mResults[2]);
        }
        else if (userAnswer1.equals("None of the above") && !userAnswer2.equals("None of these symptoms")){
            textView.setText(mResults[1]);
        }
        else {
            textView.setText(mResults[0]);
        }


    }
}
