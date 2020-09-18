package com.example.quizzlet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    ProgressBar mProgressbar;
    TextView mQuestionTextView;
    int mIndex;
    int mScore;

    @NonNull
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mProgressbar = findViewById(R.id.progress_bar);
        mScoreTextView = findViewById(R.id.score);
        mQuestionTextView = findViewById(R.id.question_text_view);
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        } else {
            mScore = 0;
            mIndex = 0;
        }
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });

        int Question = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(Question);
    }



    public void updateQuestion(){
        mIndex = (mIndex + 1)% 13;
        if(mIndex == 0){
            mScore = 0;
            mProgressbar.setProgress(0);
        }
        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());
        mProgressbar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
    }

    public void  checkAnswer(Boolean checkedAnswer){
        boolean correctAnswer = mQuestionBank[mIndex].getAnswer();

        if(correctAnswer == checkedAnswer){
             Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
             mScore =mScore+1;
        }else{
             Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}