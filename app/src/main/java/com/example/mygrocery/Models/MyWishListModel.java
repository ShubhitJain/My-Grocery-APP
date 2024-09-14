package com.example.mygrocery.Models;

public class MyWishListModel {
    private long freeCoupens;
    private boolean payementMethod;
    private String productCuttedPrice;
    private String productImage;
    private String productPrice;
    private String producttitle;
    private String rating;
    private long totalRatings;

    public MyWishListModel(String productImage2, String producttitle2, long freeCoupens2, String rating2, long totalRatings2, String productPrice2, String productCuttedPrice2, boolean payementMethod2) {
        this.productImage = productImage2;
        this.producttitle = producttitle2;
        this.freeCoupens = freeCoupens2;
        this.rating = rating2;
        this.totalRatings = totalRatings2;
        this.productPrice = productPrice2;
        this.productCuttedPrice = productCuttedPrice2;
        this.payementMethod = payementMethod2;
    }

    public String getProductImage() {
        return this.productImage;
    }

    public void setProductImage(String productImage2) {
        this.productImage = productImage2;
    }

    public String getProducttitle() {
        return this.producttitle;
    }

    public void setProducttitle(String producttitle2) {
        this.producttitle = producttitle2;
    }

    public long getFreeCoupens() {
        return this.freeCoupens;
    }

    public void setFreeCoupens(long freeCoupens2) {
        this.freeCoupens = freeCoupens2;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating2) {
        this.rating = rating2;
    }

    public long getTotalRatings() {
        return this.totalRatings;
    }

    public void setTotalRatings(long totalRatings2) {
        this.totalRatings = totalRatings2;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(String productPrice2) {
        this.productPrice = productPrice2;
    }

    public String getProductCuttedPrice() {
        return this.productCuttedPrice;
    }

    public void setProductCuttedPrice(String productCuttedPrice2) {
        this.productCuttedPrice = productCuttedPrice2;
    }

    public boolean getPayementMethod() {
        return this.payementMethod;
    }

    public void setPayementMethod(boolean payementMethod2) {
        this.payementMethod = payementMethod2;
    }
}
