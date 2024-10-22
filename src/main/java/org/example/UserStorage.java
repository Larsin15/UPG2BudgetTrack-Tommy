package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class UserStorage {
    private Map<String, User> usersList;
    private String fileName = "users.txt";

    public UserStorage() {
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