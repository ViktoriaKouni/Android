package com.example.corona.model;

public class User {

    private String userName;
    private String status;
    private String email;

    public User(String userName,String status,String email){
        this.userName=userName;
        this.status=status;
        this.email=email;
    }

    public String getUserName() {
        return userName;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

}
