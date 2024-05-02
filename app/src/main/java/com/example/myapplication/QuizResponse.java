package com.example.myapplication;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.io.Serializable;

public class QuizResponse implements Serializable {

    @SerializedName("quiz")
    private List<Quiz> quizList;

    public List<Quiz> getQuizList() {
        return quizList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            stringBuilder.append("Question: ").append(quiz.getQuestion()).append("\n");
            stringBuilder.append("Options: ").append(quiz.getOptions()).append("\n");
            stringBuilder.append("Correct Answer: ").append(quiz.getCorrectAnswer()).append("\n\n");
        }
        return stringBuilder.toString();
    }
}

class Quiz implements Serializable {
    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("options")
    private List<String> options;

    @SerializedName("question")
    private String question;

    @SerializedName("my_answer")
    private String myAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }
}
