package com.example.mygrocery.Models;

public class MyOrderitemModel {
    private String dileveryStatus;
    private int orderImage;
    private String orderTitle;
    private int rating;

    public MyOrderitemModel(int orderImage2, String orderTitle2, String dileveryStatus2, int rating2) {
        this.orderImage = orderImage2;
        this.orderTitle = orderTitle2;
        this.dileveryStatus = dileveryStatus2;
        this.rating = rating2;
    }

    public int getOrderImage() {
        return this.orderImage;
    }

    public void setOrderImage(int orderImage2) {
        this.orderImage = orderImage2;
    }

    public String getOrderTitle() {
        return this.orderTitle;
    }

    public void setOrderTitle(String orderTitle2) {
        this.orderTitle = orderTitle2;
    }

    public String getDileveryStatus() {
        return this.dileveryStatus;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating2) {
        this.rating = rating2;
    }

    public void setDileveryStatus(String dileveryStatus2) {
        this.dileveryStatus = dileveryStatus2;
    }
}
