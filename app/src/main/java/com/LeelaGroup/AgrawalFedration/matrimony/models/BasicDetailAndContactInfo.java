package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 10-07-2017.
 */

public class BasicDetailAndContactInfo {

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    @SerializedName("mat_basic_cont_id")
    public String mat_basic_cont_id;

    public String getMat_basic_cont_id() {
        return mat_basic_cont_id;
    }

    public void setMat_basic_cont_id(String mat_basic_cont_id) {
        this.mat_basic_cont_id = mat_basic_cont_id;
    }

    @SerializedName("mat_reg_religion")
    private String mat_reg_religion;

    @SerializedName("mat_reg_caste")
    private String mat_reg_caste;

    @SerializedName("mat_reg_subcaste")
    private String mat_reg_subcaste;

    @SerializedName("mat_sess")
    private String mat_sess;
    @SerializedName("mreg_prof_pic")
    private String mreg_prof_pic;
    @SerializedName("mreg_am")
    private String mreg_am;
    @SerializedName("mreg_fname")
    private String mreg_fname;
    @SerializedName("mreg_mname")
    private String mreg_mname;
    @SerializedName("mreg_lname")
    private String mreg_lname;
    @SerializedName("mreg_birth_place")
    private String mreg_birth_place;
    @SerializedName("mreg_birth_time")
    private String mreg_birth_time;
    @SerializedName("mreg_native_place")
    private String mreg_native_place;
    @SerializedName("mreg_dob")
    private String mreg_dob;
    @SerializedName("mreg_age")
    private String mreg_age;
    @SerializedName("mreg_marital_status")
    private String mreg_marital_status;
    @SerializedName("mreg_gender")
    private String mreg_gender;
    @SerializedName("mreg_no_child")
    private String mreg_no_child;
    @SerializedName("mreg_child_leave_status")
    private String mreg_child_leave_status;
    @SerializedName("mreg_mother_tongue")
    private String mreg_mother_tongue;
    @SerializedName("mreg_about_me")
    private String mreg_about_me;

    @SerializedName("mreg_landline")
    @Expose
    private String mregLandline;
    @SerializedName("mreg_phone")
    @Expose
    private String mregPhone;
    @SerializedName("mreg_email")
    @Expose
    private String mregEmail;
    @SerializedName("mreg_addr")
    @Expose
    private String mregAddr;
    @SerializedName("mreg_country")
    @Expose
    private String mregCountry;
    @SerializedName("mreg_state")
    @Expose
    private String mregState;
    @SerializedName("mreg_city")
    @Expose
    private String mregCity;
    @SerializedName("mreg_pincode")
    @Expose
    private String mregPincode;
    @SerializedName("mreg_resid_status")
    @Expose
    private String mregResidStatus;

    public String getMregLandline() {
        return mregLandline;
    }

    public void setMregLandline(String mregLandline) {
        this.mregLandline = mregLandline;
    }

    public String getMregPhone() {
        return mregPhone;
    }

    public void setMregPhone(String mregPhone) {
        this.mregPhone = mregPhone;
    }

    public String getMregEmail() {
        return mregEmail;
    }

    public void setMregEmail(String mregEmail) {
        this.mregEmail = mregEmail;
    }

    public String getMregAddr() {
        return mregAddr;
    }

    public void setMregAddr(String mregAddr) {
        this.mregAddr = mregAddr;
    }

    public String getMregCountry() {
        return mregCountry;
    }

    public void setMregCountry(String mregCountry) {
        this.mregCountry = mregCountry;
    }

    public String getMregState() {
        return mregState;
    }

    public void setMregState(String mregState) {
        this.mregState = mregState;
    }

    public String getMregCity() {
        return mregCity;
    }

    public void setMregCity(String mregCity) {
        this.mregCity = mregCity;
    }

    public String getMregPincode() {
        return mregPincode;
    }

    public void setMregPincode(String mregPincode) {
        this.mregPincode = mregPincode;
    }

    public String getMregResidStatus() {
        return mregResidStatus;
    }

    public void setMregResidStatus(String mregResidStatus) {
        this.mregResidStatus = mregResidStatus;
    }

    public String getMat_sess() {
        return mat_sess;
    }

    public void setMat_sess(String mat_sess) {
        this.mat_sess = mat_sess;
    }

    public String getMreg_prof_pic() {
        return mreg_prof_pic;
    }

    public void setMreg_prof_pic(String mreg_prof_pic) {
        this.mreg_prof_pic = mreg_prof_pic;
    }

    public String getMreg_am() {
        return mreg_am;
    }

    public void setMreg_am(String mreg_am) {
        this.mreg_am = mreg_am;
    }

    public String getMreg_fname() {
        return mreg_fname;
    }

    public void setMreg_fname(String mreg_fname) {
        this.mreg_fname = mreg_fname;
    }

    public String getMreg_mname() {
        return mreg_mname;
    }

    public void setMreg_mname(String mreg_mname) {
        this.mreg_mname = mreg_mname;
    }

    public String getMreg_lname() {
        return mreg_lname;
    }

    public void setMreg_lname(String mreg_lname) {
        this.mreg_lname = mreg_lname;
    }

    public String getMreg_birth_place() {
        return mreg_birth_place;
    }

    public void setMreg_birth_place(String mreg_birth_place) {
        this.mreg_birth_place = mreg_birth_place;
    }

    public String getMreg_birth_time() {
        return mreg_birth_time;
    }

    public void setMreg_birth_time(String mreg_birth_time) {
        this.mreg_birth_time = mreg_birth_time;
    }

    public String getMreg_native_place() {
        return mreg_native_place;
    }

    public void setMreg_native_place(String mreg_native_place) {
        this.mreg_native_place = mreg_native_place;
    }

    public String getMreg_dob() {
        return mreg_dob;
    }

    public void setMreg_dob(String mreg_dob) {
        this.mreg_dob = mreg_dob;
    }

    public String getMreg_age() {
        return mreg_age;
    }

    public void setMreg_age(String mreg_age) {
        this.mreg_age = mreg_age;
    }

    public String getMreg_marital_status() {
        return mreg_marital_status;
    }

    public void setMreg_marital_status(String mreg_marital_status) {
        this.mreg_marital_status = mreg_marital_status;
    }

    public String getMreg_gender() {
        return mreg_gender;
    }

    public void setMreg_gender(String mreg_gender) {
        this.mreg_gender = mreg_gender;
    }

    public String getMreg_no_child() {
        return mreg_no_child;
    }

    public void setMreg_no_child(String mreg_no_child) {
        this.mreg_no_child = mreg_no_child;
    }

    public String getMreg_child_leave_status() {
        return mreg_child_leave_status;
    }

    public void setMreg_child_leave_status(String mreg_child_leave_status) {
        this.mreg_child_leave_status = mreg_child_leave_status;
    }

    public String getMreg_mother_tongue() {
        return mreg_mother_tongue;
    }

    public void setMreg_mother_tongue(String mreg_mother_tongue) {
        this.mreg_mother_tongue = mreg_mother_tongue;
    }

    public String getMreg_about_me() {
        return mreg_about_me;
    }

    public void setMreg_about_me(String mreg_about_me) {
        this.mreg_about_me = mreg_about_me;
    }


    public String getMat_reg_religion() {
        return mat_reg_religion;
    }

    public void setMat_reg_religion(String mat_reg_religion) {
        this.mat_reg_religion = mat_reg_religion;
    }

    public String getMat_reg_caste() {
        return mat_reg_caste;
    }

    public void setMat_reg_caste(String mat_reg_caste) {
        this.mat_reg_caste = mat_reg_caste;
    }

    public String getMat_reg_subcaste() {
        return mat_reg_subcaste;
    }

    public void setMat_reg_subcaste(String mat_reg_subcaste) {
        this.mat_reg_subcaste = mat_reg_subcaste;
    }
}