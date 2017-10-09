package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 08-07-2017.
 */

public class PersonalDetailPojo {

    boolean success;

    String message;


    @SerializedName("exam")
    String exam;

    @SerializedName("sess")
    String sess;

    @SerializedName("pd_fname")
    String pd_fname;

    @SerializedName("pd_lname")
    String pd_lname;

    @SerializedName("pd_dob")
    String pd_dob;

    @SerializedName("pd_father_name")
    String pd_father_name;

    @SerializedName("pd_mother_name")
    String pd_mother_name;

    @SerializedName("pd_gender")
    String pd_gender;

    @SerializedName("pd_mob1")
    String pd_mob1;

    @SerializedName("pd_mob2")
    String pd_mob2;

    @SerializedName("pd_email")
    String pd_email;

    @SerializedName("pd_addr")
    String pd_addr;

    @SerializedName("pd_pincode")
    String pd_pincode;

    @SerializedName("pd_city")
    String pd_city;

    @SerializedName("pd_state")
    String pd_state;

    @SerializedName("pd_pwd")
    String pd_pwd;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }
    public String getPd_fname() {
        return pd_fname;
    }

    public void setPd_fname(String pd_fname) {
        this.pd_fname = pd_fname;
    }

    public String getPd_lname() {
        return pd_lname;
    }

    public void setPd_lname(String pd_lname) {
        this.pd_lname = pd_lname;
    }

    public String getPd_dob() {
        return pd_dob;
    }

    public void setPd_dob(String pd_dob) {
        this.pd_dob = pd_dob;
    }

    public String getPd_father_name() {
        return pd_father_name;
    }

    public void setPd_father_name(String pd_father_name) {
        this.pd_father_name = pd_father_name;
    }

    public String getPd_mother_name() {
        return pd_mother_name;
    }

    public void setPd_mother_name(String pd_mother_name) {
        this.pd_mother_name = pd_mother_name;
    }

    public String getPd_gender() {
        return pd_gender;
    }

    public void setPd_gender(String pd_gender) {
        this.pd_gender = pd_gender;
    }

    public String getPd_mob1() {
        return pd_mob1;
    }

    public void setPd_mob1(String pd_mob1) {
        this.pd_mob1 = pd_mob1;
    }

    public String getPd_mob2() {
        return pd_mob2;
    }

    public void setPd_mob2(String pd_mob2) {
        this.pd_mob2 = pd_mob2;
    }

    public String getPd_email() {
        return pd_email;
    }

    public void setPd_email(String pd_email) {
        this.pd_email = pd_email;
    }

    public String getPd_addr() {
        return pd_addr;
    }

    public void setPd_addr(String pd_addr) {
        this.pd_addr = pd_addr;
    }

    public String getPd_pincode() {
        return pd_pincode;
    }

    public void setPd_pincode(String pd_pincode) {
        this.pd_pincode = pd_pincode;
    }

    public String getPd_city() {
        return pd_city;
    }

    public void setPd_city(String pd_city) {
        this.pd_city = pd_city;
    }

    public String getPd_state() {
        return pd_state;
    }

    public void setPd_state(String pd_state) {
        this.pd_state = pd_state;
    }

    public String getPd_pwd() {
        return pd_pwd;
    }

    public void setPd_pwd(String pd_pwd) {
        this.pd_pwd = pd_pwd;
    }


    @SerializedName("sname")
    @Expose
    private String sname;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


    @SerializedName("city_name")
    @Expose
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
