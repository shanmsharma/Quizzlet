package com.example.quizzlet;

public class TrueFalse {
    int mQuestionID;
    Boolean mAnswer;

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public Boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Boolean answer) {
        mAnswer = answer;
    }

    public TrueFalse(int questionID ,Boolean answer) {
        mQuestionID = questionID;
        mAnswer = answer;
    }
}
