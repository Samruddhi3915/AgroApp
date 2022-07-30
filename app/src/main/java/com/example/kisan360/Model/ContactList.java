package com.example.kisan360.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ContactList implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Contact_List")
    @Expose
    private List<ContactModel> contactlist = null;

    public ContactList(){

    }

    public ContactList(String message, List<ContactModel> contactlist) {
        this.message = message;
        this.contactlist = contactlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContactModel> getContactlist() {
        return contactlist;
    }

    public void setContactlist(List<ContactModel> contactlist) {
        this.contactlist = contactlist;
    }
}
