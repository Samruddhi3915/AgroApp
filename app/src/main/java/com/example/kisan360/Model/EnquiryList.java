package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnquiryList {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Enquiry_List")
    @Expose
    private List<EnquiryModel> enquirylist = null;

    public EnquiryList(){

    }

    public EnquiryList(String message, List<EnquiryModel> enquirylist) {
        this.message = message;
        this.enquirylist = enquirylist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EnquiryModel> getEnquirylist() {
        return enquirylist;
    }

    public void setEnquirylist(List<EnquiryModel> enquirylist) {
        this.enquirylist = enquirylist;
    }
}
