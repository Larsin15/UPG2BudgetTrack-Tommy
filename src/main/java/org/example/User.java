package org.example;

import java.util.Scanner;
import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    private Integer userId;

    public User(String firstName, String lastName, Integer userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
