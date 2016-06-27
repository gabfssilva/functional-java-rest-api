package com.thedevpiece.model;

public class User {
    private String id;
    private String username;
    private String email;
    private String occupation;
    private Integer age;

    public User() {
    }

    public User(String id, String username, String email, String occupation, Integer age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.occupation = occupation;
        this.age = age;
    }

    public User withId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getOccupation() {
        return occupation;
    }

    public Integer getAge() {
        return age;
    }
}
