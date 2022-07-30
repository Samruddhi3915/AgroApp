package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchemeList {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Scheme_List")
    @Expose
    private List<SchemeModel> schemelist = null;

    public SchemeList(){

    }

    public SchemeList(String message, List<SchemeModel> schemelist) {
        this.message = message;
        this.schemelist = schemelist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SchemeModel> getSchemelist() {
        return schemelist;
    }

    public void setSchemelist(List<SchemeModel> schemelist) {
        this.schemelist = schemelist;
    }
}
