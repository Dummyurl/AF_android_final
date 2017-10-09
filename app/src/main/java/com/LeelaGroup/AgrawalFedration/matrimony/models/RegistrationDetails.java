package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 15-07-2017.
 */

public class RegistrationDetails {

    @SerializedName("mat_fname")
    String mat_fname;
    @SerializedName("mat_mname")
    String mat_mname;
    @SerializedName("mat_lname")
    String mat_lname;
    @SerializedName("mat_email")
    String mat_email;
    @SerializedName("mat_phone")
    String mat_phone;
    @SerializedName("mat_gender")
    String mat_gender;
    @SerializedName("mat_pwd")
    String mat_pwd;
    @SerializedName("mat_id")
    String mat_id;

    public String getMat_id() {
        return mat_id;
    }

    public void setMat_id(String mat_id) {
        this.mat_id = mat_id;
    }


    public String getMat_fname() {
        return mat_fname;
    }

    public void setMat_fname(String mat_fname) {
        this.mat_fname = mat_fname;
    }

    public String getMat_mname() {
        return mat_mname;
    }

    public void setMat_mname(String mat_mname) {
        this.mat_mname = mat_mname;
    }

    public String getMat_lname() {
        return mat_lname;
    }

    public void setMat_lname(String mat_lname) {
        this.mat_lname = mat_lname;
    }

    public String getMat_email() {
        return mat_email;
    }

    public void setMat_email(String mat_email) {
        this.mat_email = mat_email;
    }

    public String getMat_phone() {
        return mat_phone;
    }

    public void setMat_phone(String mat_phone) {
        this.mat_phone = mat_phone;
    }

    public String getMat_gender() {
        return mat_gender;
    }

    public void setMat_gender(String mat_gender) {
        this.mat_gender = mat_gender;
    }

    public String getMat_pwd() {
        return mat_pwd;
    }

    public void setMat_pwd(String mat_pwd) {
        this.mat_pwd = mat_pwd;
    }
}
