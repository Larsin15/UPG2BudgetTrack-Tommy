package org.example;

import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    private String userId;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = lastName + UUID.randomUUID().toString();
    }

    public String getUserId() {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
