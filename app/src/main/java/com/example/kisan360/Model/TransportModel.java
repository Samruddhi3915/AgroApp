package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportModel {
    @SerializedName("pk_id")
    @Expose
    private String pk_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicle_no;
    @SerializedName("phone")
    @Expose
    private String phone;


    public TransportModel(){

    }

    public TransportModel(String pk_id, String name, String rate, String vehicle_no, String phone) {
        this.pk_id = pk_id;
        this.name = name;
        this.rate = rate;
        this.vehicle_no = vehicle_no;
        this.phone = phone;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
