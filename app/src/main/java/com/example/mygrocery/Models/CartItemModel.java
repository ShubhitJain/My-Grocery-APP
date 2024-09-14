package com.example.mygrocery.Models;

public class CartItemModel {
    public static final int Total_amount = 1;
    public static final int cartItem = 0;
    private int coupensApplied;
    private String cuttedPrice;
    private String deliverprice;
    private int freeCoupens;
    private int offersApplied;
    private int productQuantity;
    private String productTitle;
    private int productimage;
    private String productprice;
    private String saved_amount;
    private String total_amount;
    private String total_items;
    private String total_items_price;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public CartItemModel(int type2, int productimage2, String productTitle2, int freeCoupens2, String productprice2, String cuttedPrice2, int productQuantity2, int offersApplied2, int coupensApplied2) {
        this.type = type2;
        this.productimage = productimage2;
        this.productTitle = productTitle2;
        this.freeCoupens = freeCoupens2;
        this.productprice = productprice2;
        this.cuttedPrice = cuttedPrice2;
        this.productQuantity = productQuantity2;
        this.offersApplied = offersApplied2;
        this.coupensApplied = coupensApplied2;
    }

    public int getProductimage() {
        return this.productimage;
    }

    public void setProductimage(int productimage2) {
        this.productimage = productimage2;
    }

    public String getProductTitle() {
        return this.productTitle;
    }

    public void setProductTitle(String productTitle2) {
        this.productTitle = productTitle2;
    }

    public int getFreeCoupens() {
        return this.freeCoupens;
    }

    public void setFreeCoupens(int freeCoupens2) {
        this.freeCoupens = freeCoupens2;
    }

    public String getProductprice() {
        return this.productprice;
    }

    public void setProductprice(String productprice2) {
        this.productprice = productprice2;
    }

    public String getCuttedPrice() {
        return this.cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice2) {
        this.cuttedPrice = cuttedPrice2;
    }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(int productQuantity2) {
        this.productQuantity = productQuantity2;
    }

    public int getOffersApplied() {
        return this.offersApplied;
    }

    public void setOffersApplied(int offersApplied2) {
        this.offersApplied = offersApplied2;
    }

    public int getCoupensApplied() {
        return this.coupensApplied;
    }

    public void setCoupensApplied(int coupensApplied2) {
        this.coupensApplied = coupensApplied2;
    }

    public CartItemModel(int type2, String total_items2, String total_items_price2, String deliverprice2, String saved_amount2, String total_amount2) {
        this.type = type2;
        this.total_items = total_items2;
        this.total_items_price = total_items_price2;
        this.deliverprice = deliverprice2;
        this.saved_amount = saved_amount2;
        this.total_amount = total_amount2;
    }

    public String getTotal_items() {
        return this.total_items;
    }

    public void setTotal_items(String total_items2) {
        this.total_items = total_items2;
    }

    public String getTotal_items_price() {
        return this.total_items_price;
    }

    public void setTotal_items_price(String total_items_price2) {
        this.total_items_price = total_items_price2;
    }

    public String getDeliverprice() {
        return this.deliverprice;
    }

    public void setDeliverprice(String deliverprice2) {
        this.deliverprice = deliverprice2;
    }

    public String getSaved_amount() {
        return this.saved_amount;
    }

    public void setSaved_amount(String saved_amount2) {
        this.saved_amount = saved_amount2;
    }

    public String getTotal_amount() {
        return this.total_amount;
    }

    public void setTotal_amount(String total_amount2) {
        this.total_amount = total_amount2;
    }
}
