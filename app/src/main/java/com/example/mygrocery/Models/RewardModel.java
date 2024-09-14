package com.example.mygrocery.Models;

public class RewardModel {
    private String coupenBody;
    private String expiryDate;
    private String title;

    public RewardModel(String title2, String expiryDate2, String coupenBody2) {
        this.title = title2;
        this.expiryDate = expiryDate2;
        this.coupenBody = coupenBody2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String expiryDate2) {
        this.expiryDate = expiryDate2;
    }

    public String getCoupenBody() {
        return this.coupenBody;
    }

    public void setCoupenBody(String coupenBody2) {
        this.coupenBody = coupenBody2;
    }
}
