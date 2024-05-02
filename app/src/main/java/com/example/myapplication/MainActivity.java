package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private final static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,};
//
//    public void verifyStoragePermissions(Activity activity) {
//
//        try {
//            //检测是否有写的权限
//            int permission = ActivityCompat.checkSelfPermission(activity,
//                    "android.permission.WRITE_EXTERNAL_STORAGE");
//            if (permission != PackageManager.PERMISSION_GRANTED) {
//                // 没有写的权限，去申请写的权限，会弹出对话框
//                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void init() {
        // 初始化 JsonHelper 和 Database
        JsonHelper.init(this);
        Database.initialize();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



        TextView signup = findViewById(R.id.text_need_account);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        EditText editUsername = findViewById(R.id.edit_username);
        EditText editPassword = findViewById(R.id.edit_password);

        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(view -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValidUser = Database.checkUser(username, password);
                if (isValidUser) {
                    Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}