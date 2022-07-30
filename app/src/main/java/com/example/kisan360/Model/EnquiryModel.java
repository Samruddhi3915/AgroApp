package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnquiryModel {
    @SerializedName("pk_id")
    @Expose
    private String pk_id;
    @SerializedName("product_name")
    @Expose
    private String product_name;
    @SerializedName("product_price")
    @Expose
    private String 	product_price;
    @SerializedName("seller_name")
    @Expose
    private String 	seller_name;
    @SerializedName("name")
    @Expose
    private String 	name;
    @SerializedName("phone")
    @Expose
    private String 	phone;
    @SerializedName("address")
    @Expose
    private String 	address;
    @SerializedName("quantity")
    @Expose
    private String 	quantity;
    @SerializedName("bid_price")
    @Expose
    private String 	bid_price;

    public EnquiryModel(){

    }

    public EnquiryModel(String pk_id, String product_name, String product_price, String seller_name, String name, String phone, String address, String quantity, String bid_price) {
        this.pk_id = pk_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.seller_name = seller_name;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.quantity = quantity;
        this.bid_price = bid_price;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBid_price() {
        return bid_price;
    }

    public void setBid_price(String bid_price) {
        this.bid_price = bid_price;
    }
}
