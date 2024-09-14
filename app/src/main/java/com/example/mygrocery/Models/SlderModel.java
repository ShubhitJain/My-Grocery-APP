package com.example.mygrocery.Models;

public class SlderModel {
    private String BackgroundColor;
    private String banner;

    public SlderModel(String banner2, String backgroundColor) {
        this.banner = banner2;
        this.BackgroundColor = backgroundColor;
    }

    public String getBanner() {
        return this.banner;
    }

    public void setBanner(String banner2) {
        this.banner = banner2;
    }

    public String getBackgroundColor() {
        return this.BackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.BackgroundColor = backgroundColor;
    }
}
