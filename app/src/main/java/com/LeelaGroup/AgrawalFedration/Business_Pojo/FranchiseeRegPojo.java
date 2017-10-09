package com.LeelaGroup.AgrawalFedration.Business_Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 15-07-2017.
 */

public class FranchiseeRegPojo {
    @SerializedName("Fran_fname")
    String Fran_fname;
    @SerializedName("Fran_lname")
    String Fran_lname;
    @SerializedName("Fran_email")
    String Fran_email;
    @SerializedName("Fran_mobile")
    String Fran_mobile;
    @SerializedName("Fran_address")
    String Fran_address;
    @SerializedName("Fran_country")
    String Fran_country;
    @SerializedName("Fran_state")
    String Fran_state;
    @SerializedName("Fran_city")
    String Fran_city;
    @SerializedName("Fran_chapter")
    String Fran_chapter;
    @SerializedName("Fran_gender")
    String Fran_gender;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFran_fname() {
        return Fran_fname;
    }

    public void setFran_fname(String fran_fname) {
        Fran_fname = fran_fname;
    }

    public String getFran_lname() {
        return Fran_lname;
    }

    public void setFran_lname(String fran_lname) {
        Fran_lname = fran_lname;
    }

    public String getFran_email() {
        return Fran_email;
    }

    public void setFran_email(String fran_email) {
        Fran_email = fran_email;
    }

    public String getFran_mobile() {
        return Fran_mobile;
    }

    public void setFran_mobile(String fran_mobile) {
        Fran_mobile = fran_mobile;
    }

    public String getFran_address() {
        return Fran_address;
    }

    public void setFran_address(String fran_address) {
        Fran_address = fran_address;
    }

    public String getFran_country() {
        return Fran_country;
    }

    public void setFran_country(String fran_country) {
        Fran_country = fran_country;
    }

    public String getFran_state() {
        return Fran_state;
    }

    public void setFran_state(String fran_state) {
        Fran_state = fran_state;
    }

    public String getFran_city() {
        return Fran_city;
    }

    public void setFran_city(String fran_city) {
        Fran_city = fran_city;
    }

    public String getFran_chapter() {
        return Fran_chapter;
    }

    public void setFran_chapter(String fran_chapter) {
        Fran_chapter = fran_chapter;
    }

    public String getFran_gender() {
        return Fran_gender;
    }

    public void setFran_gender(String fran_gender) {
        Fran_gender = fran_gender;
    }
}
