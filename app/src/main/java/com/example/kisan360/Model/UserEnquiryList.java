package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserEnquiryList implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Enquiry_List")
    @Expose
    private List<UserEnquiryModel> enquirylist = null;


    public UserEnquiryList(){

    }

    public UserEnquiryList(String message, List<UserEnquiryModel> enquirylist) {
        this.message = message;
        this.enquirylist = enquirylist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserEnquiryModel> getEnquirylist() {
        return enquirylist;
    }

    public void setEnquirylist(List<UserEnquiryModel> enquirylist) {
        this.enquirylist = enquirylist;
    }
}
