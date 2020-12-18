package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mQuestionView;
    private TextView mButtonChoice1;
    private TextView mButtonChoice2;
    private TextView mButtonChoice3;
    private TextView mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    private String userAnswers [] = {"test", "test2"};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_quiz, container, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionView = (TextView)findViewById(R.id.quizQuestion);
        mButtonChoice1 = (TextView)findViewById(R.id.checkedTextView);
        mButtonChoice2 = (TextView)findViewById(R.id.checkedTextView2);
        mButtonChoice3 = (TextView)findViewById(R.id.checkedTextView3);
        mButtonChoice4 = (TextView)findViewById(R.id.checkedTextView4);

        updateQuestion();

        //Button 1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText() == mAnswer) {
                    // None of the above
                    mScore++;
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice1.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice1.getText() + "";
                        finish();

                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);

                        startActivity(intent);
                    }

                }
                else{
                    // anything else
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice1.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice1.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }
                }
            }
        });

        //Button 2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText() == mAnswer) {
                    // None of the above
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice2.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice2.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }
                    mScore++;
                }
                else{
                    // anything else
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice2.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice2.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }
                }
            }
        });

        //Button 3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText() == mAnswer) {
                    // None of the above
                    mScore++;
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice3.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice3.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }
                }
                else{
                    // anything else
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        //makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice3.getText() + "";
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice3.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }
                }
            }
        });

        //Button 4
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice4.getText() == mAnswer) {
                    // None of the above
                    mScore = mScore + 1;
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        userAnswers[mQuestionNumber - 1] = mButtonChoice4.getText() + "";
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice4.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }




                }
                else{
                    // anything else
                    if (mQuestionLibrary.getNumberOfQuestions() - 1 >= mQuestionNumber) {
                        userAnswers[mQuestionNumber - 1] = mButtonChoice4.getText() + "";
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    } else {
                        //Toast.makeText(QuizActivity.this, userAnswers[mQuestionNumber - 1], Toast.LENGTH_SHORT).show();
                        userAnswers[mQuestionNumber - 1] = mButtonChoice4.getText() + "";
                        finish();
                        Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                        intent.putExtra("userAnswer1", userAnswers[0]);
                        intent.putExtra("userAnswer2", userAnswers[1]);
                        startActivity(intent);
                    }

                }

            }
        });
    }


    private void updateQuestion() {

        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswers(mQuestionNumber);


        mQuestionNumber++;



    }
}
