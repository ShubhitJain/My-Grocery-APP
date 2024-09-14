package com.example.mygrocery.Models;

public class CategoryModel {
    private String CategoryIcon;
    private String CategoryName;

    public CategoryModel(String categoryIcon, String categoryName) {
        this.CategoryIcon = categoryIcon;
        this.CategoryName = categoryName;
    }

    public String getCategoryIcon() {
        return this.CategoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.CategoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return this.CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }
}
