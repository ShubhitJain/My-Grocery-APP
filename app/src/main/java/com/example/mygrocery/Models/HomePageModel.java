package com.example.mygrocery.Models;

import java.util.List;

public class HomePageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int GRID_PRODUCT_VIEW = 3;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int STRIP_AD_BANNER = 1;
    private String bgColor;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    private String resource;
    private List<SlderModel> slderModelList;
    private String title;
    private int type;
    private List<MyWishListModel> viewAllProductList;

    public HomePageModel(int type2, List<SlderModel> slderModelList2) {
        this.type = type2;
        this.slderModelList = slderModelList2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public List<SlderModel> getSlderModelList() {
        return this.slderModelList;
    }

    public void setSlderModelList(List<SlderModel> slderModelList2) {
        this.slderModelList = slderModelList2;
    }

    public HomePageModel(int type2, String resource2, String bgColor2) {
        this.type = type2;
        this.resource = resource2;
        this.bgColor = bgColor2;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource2) {
        this.resource = resource2;
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(String bgColor2) {
        this.bgColor = bgColor2;
    }

    public HomePageModel(int type2, String title2, String bgColor2, List<HorizontalProductScrollModel> horizontalProductScrollModelList2, List<MyWishListModel> viewAllProductList2) {
        this.type = type2;
        this.title = title2;
        this.bgColor = bgColor2;
        this.viewAllProductList = viewAllProductList2;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList2;
    }

    public List<MyWishListModel> getViewAllProductList() {
        return this.viewAllProductList;
    }

    public void setViewAllProductList(List<MyWishListModel> viewAllProductList2) {
        this.viewAllProductList = viewAllProductList2;
    }

    public HomePageModel(int type2, String title2, String bgColor2, List<HorizontalProductScrollModel> horizontalProductScrollModelList2) {
        this.type = type2;
        this.title = title2;
        this.bgColor = bgColor2;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return this.horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList2) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList2;
    }
}
