package com.example.mygrocery.Models;

public class HorizontalProductScrollModel {
    private String horizontalProductDescription;
    private String horizontalProductImage;
    private String horizontalProductPrice;
    private String horizontalProductTitle;
    private String productId;

    public HorizontalProductScrollModel(String productId2, String horizontalProductImage2, String horizontalProductTitle2, String horizontalProductDescription2, String horizontalProductPrice2) {
        this.horizontalProductImage = horizontalProductImage2;
        this.productId = productId2;
        this.horizontalProductTitle = horizontalProductTitle2;
        this.horizontalProductDescription = horizontalProductDescription2;
        this.horizontalProductPrice = horizontalProductPrice2;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId2) {
        this.productId = productId2;
    }

    public String getHorizontalProductImage() {
        return this.horizontalProductImage;
    }

    public void setHorizontalProductImage(String horizontalProductImage2) {
        this.horizontalProductImage = horizontalProductImage2;
    }

    public String getHorizontalProductTitle() {
        return this.horizontalProductTitle;
    }

    public void setHorizontalProductTitle(String horizontalProductTitle2) {
        this.horizontalProductTitle = horizontalProductTitle2;
    }

    public String getHorizontalProductDescription() {
        return this.horizontalProductDescription;
    }

    public void setHorizontalProductDescription(String horizontalProductDescription2) {
        this.horizontalProductDescription = horizontalProductDescription2;
    }

    public String getHorizontalProductPrice() {
        return this.horizontalProductPrice;
    }

    public void setHorizontalProductPrice(String horizontalProductPrice2) {
        this.horizontalProductPrice = horizontalProductPrice2;
    }
}
