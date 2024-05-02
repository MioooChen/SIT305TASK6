package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<User> userList = new ArrayList<>();
    // current user
    private static User user = null;

    // 初始化方法，从文件中读取用户数据
    public static void initialize() {
        userList = JsonHelper.readUsers();

    }

    // 添加用户到用户数组并保存到文件
    public static void addUser(User user) {
        userList.add(user);
        JsonHelper.saveUsers(userList);
    }

    // 检查是否有该用户
    public static boolean checkUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Database.user = user;
                return true;
            }
        }
        return false;
    }

    public static boolean canRegister(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static void updateUserInterests(String username, List<String> interests) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setInterests(interests.toArray(new String[0]));
                JsonHelper.saveUsers(userList);
                return;
            }
        }
    }

    public static User currentUser() {
        return user;
    }
}
