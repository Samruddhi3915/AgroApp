package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductList {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Product_List")
    @Expose
    private List<ProductModel> productlist = null;


    public ProductList(){

    }

    public ProductList(String message, List<ProductModel> productlist) {
        this.message = message;
        this.productlist = productlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductModel> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductModel> productlist) {
        this.productlist = productlist;
    }
}
