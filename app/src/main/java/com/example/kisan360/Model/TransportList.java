package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransportList {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Transport_List")
    @Expose
    private List<TransportModel> transportlist = null;


    public TransportList(){

    }

    public TransportList(String message, List<TransportModel> transportlist) {
        this.message = message;
        this.transportlist = transportlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TransportModel> getTransportlist() {
        return transportlist;
    }

    public void setTransportlist(List<TransportModel> transportlist) {
        this.transportlist = transportlist;
    }
}
