package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    User user;
    QuizResponse quizResponse;
    private RecyclerView recyclerView;
    private QuizResponseAdapter responseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        // get username
        user = Database.currentUser();
        TextView usernameText = findViewById(R.id.username);
        usernameText.setText(user.getUsername());

        // set avatar
        ImageView avatar = findViewById(R.id.image_avatar);
        // image base64 to bitmap
        avatar.setImageBitmap(base64ToBitmap(user.getImg()));



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView taskCountTextView = findViewById(R.id.text_task_count);

        // get quiz
        // create thread
        new Thread(() -> {
            quizResponse = NetClient.fetchQuiz(String.join("&", user.getInterests()));

//            quizResponse = NetClient.fetchFake();
            System.out.println(quizResponse);
            runOnUiThread(() -> {
                if (quizResponse == null) {
                    Toast.makeText(this, "Failed to fetch quiz", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<QuizResponse> responseList = new ArrayList<>();
                responseList.add(quizResponse);

                responseAdapter = new QuizResponseAdapter(this, responseList);
                recyclerView.setAdapter(responseAdapter);
                taskCountTextView.setText("You have " + responseList.size() + " tasks due");
            });
        }).start();
    }

    // base64 to bitmap
    public Bitmap base64ToBitmap(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}