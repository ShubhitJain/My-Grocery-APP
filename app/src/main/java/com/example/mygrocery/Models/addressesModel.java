package com.example.mygrocery.Models;

public class addressesModel {
    private String Address;
    private String fullName;
    private String pincode;
    private Boolean selected;

    public addressesModel(String fullName2, String address, String pincode2, Boolean selected2) {
        this.fullName = fullName2;
        this.Address = address;
        this.pincode = pincode2;
        this.selected = selected2;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName2) {
        this.fullName = fullName2;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPincode() {
        return this.pincode;
    }

    public void setPincode(String pincode2) {
        this.pincode = pincode2;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setSelected(Boolean selected2) {
        this.selected = selected2;
    }
}
