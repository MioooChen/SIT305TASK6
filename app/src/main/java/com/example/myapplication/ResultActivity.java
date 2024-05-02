package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        QuizResponse quizResponse = intent.getSerializableExtra("quizResponse", QuizResponse.class);
        resultAdapter = new ResultAdapter(quizResponse.getQuizList());
        recyclerView.setAdapter(resultAdapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            finish();
        });
    }
}