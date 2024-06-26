package com.example.dngrocery.model;


import com.google.firebase.Timestamp;

public class User_model {
    private String phoneNumber;
    private String userName;
    private Timestamp creTimestamp;



    public User_model(String phoneNumber, String userName, Timestamp creTimestamp) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.creTimestamp = creTimestamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreTimestamp() {
        return creTimestamp;
    }

    public void setCreTimestamp(Timestamp creTimestamp) {
        this.creTimestamp = creTimestamp;
    }
}
