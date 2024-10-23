package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserStorage {
    private Map<Integer, User> usersList = new HashMap<>();
    private String fileName = "users.txt";

    public void addUser(Integer userId, User user) {
        usersList.put(userId, user);
        System.out.println("Ditt login med ID: " + userId + " är skapat.");
    }

    public Map<Integer, User> getUsers() {
        return usersList;
    }

    public User getUserById(int userId) {
        return usersList.get(userId);
    }

    public void readUsers(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            User[] readUsers = gson.fromJson(reader, User[].class);
            usersList.clear();
            for (User user : readUsers) {
                usersList.put(user.getUserId(), user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsers(String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(usersList.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}