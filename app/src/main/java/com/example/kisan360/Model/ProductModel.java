package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("pk_id")
    @Expose
    private String pk_id;
    @SerializedName("product_name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("seller_name")
    @Expose
    private String seller_name;
    @SerializedName("seller_address")
    @Expose
    private String seller_address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("min_quantity")
    @Expose
    private String 	min_quantity;
    @SerializedName("total_quantity")
    @Expose
    private String total_quantity;



    public ProductModel(){

    }

    public ProductModel(String pk_id, String name, String image, String description, String category, String seller_name, String seller_address, String phone, String status, String created_date, String price, String min_quantity, String total_quantity) {
        this.pk_id = pk_id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.seller_name = seller_name;
        this.seller_address = seller_address;
        this.phone = phone;
        this.status = status;
        this.created_date = created_date;
        this.price = price;
        this.min_quantity = min_quantity;
        this.total_quantity = total_quantity;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(String seller_address) {
        this.seller_address = seller_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMin_quantity() {
        return min_quantity;
    }

    public void setMin_quantity(String min_quantity) {
        this.min_quantity = min_quantity;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }
}
