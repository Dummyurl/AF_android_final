package com.LeelaGroup.AgrawalFedration.Medical_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 06-07-2017.
 */

public class Medical {

    boolean success;

    public String getFlagstatus() {
        return flagstatus;
    }

    public void setFlagstatus(String flagstatus) {
        this.flagstatus = flagstatus;
    }

    @SerializedName("flagstatus")
    String flagstatus;



    @SerializedName("cid")
    String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @SerializedName("city_name")
    String city_name;

    @SerializedName("cname")
    String cname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @SerializedName("sname")
    String sname;

    @SerializedName("med_id")
    String med_id;

    @SerializedName("med_logo")
     String med_logo;

    @SerializedName("message")
    String message;

    @SerializedName("med_type")
    private String Category;

    @SerializedName("med_name")
    private String businessName1;

    @SerializedName("med_addr")
    private String businessAddress1;

    @SerializedName("med_pincode")
    private String businessPincode1;

    @SerializedName("med_phone")
    private String businessContact1;

    @SerializedName("med_country")
    private String country;

    @SerializedName("med_state")
    private String state;

    @SerializedName("med_ciy")
    private String city;

    @SerializedName("med_educ")
    private String businessQualification1;

    @SerializedName("med_open_time")
    private String openTime;

    @SerializedName("med_close_time")
    private String CloseTime;

    @SerializedName("med_about")
    private String aboutBusiness1;

    @SerializedName("med_website")
    private String businessWebsite1;

    @SerializedName("med_cont_name")
    private String med_cont_name;

    @SerializedName("med_cont_phone")
    private String med_cont_phone;

    @SerializedName("med_cont_email")
    private String med_cont_email;

   @SerializedName("med_cont_desig")
    private String med_cont_desig;

    public String getMed_logo() {
        return med_logo;
    }

    @SerializedName("med_cont_detail")
    private String med_cont_detail;



    public String getMed_id() {
        return med_id;
    }

    public void setMed_id(String med_id) {
        this.med_id = med_id;
    }

    public void setMed_logo(String med_logo) {
        this.med_logo = med_logo;
    }


    public String getMed_cont_name() {
        return med_cont_name;
    }

    public void setMed_cont_name(String med_cont_name) {
        this.med_cont_name = med_cont_name;
    }

    public String getMed_cont_phone() {
        return med_cont_phone;
    }

    public void setMed_cont_phone(String med_cont_phone) {
        this.med_cont_phone = med_cont_phone;
    }

    public String getMed_cont_email() {
        return med_cont_email;
    }

    public void setMed_cont_email(String med_cont_email) {
        this.med_cont_email = med_cont_email;
    }

    public String getMed_cont_desig() {
        return med_cont_desig;
    }

    public void setMed_cont_desig(String med_cont_desig) {
        this.med_cont_desig = med_cont_desig;
    }

    public String getMed_cont_detail() {
        return med_cont_detail;
    }

    public void setMed_cont_detail(String med_cont_detail) {
        this.med_cont_detail = med_cont_detail;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getBusinessName1() {
        return businessName1;
    }

    public void setBusinessName1(String businessName1) {
        this.businessName1 = businessName1;
    }

    public String getBusinessAddress1() {
        return businessAddress1;
    }

    public void setBusinessAddress1(String businessAddress1) {
        this.businessAddress1 = businessAddress1;
    }

    public String getBusinessPincode1() {
        return businessPincode1;
    }

    public void setBusinessPincode1(String businessPincode1) {
        this.businessPincode1 = businessPincode1;
    }

    public String getBusinessContact1() {
        return businessContact1;
    }

    public void setBusinessContact1(String businessContact1) {
        this.businessContact1 = businessContact1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessQualification1() {
        return businessQualification1;
    }

    public void setBusinessQualification1(String businessQualification1) {
        this.businessQualification1 = businessQualification1;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(String closeTime) {
        CloseTime = closeTime;
    }

    public String getAboutBusiness1() {
        return aboutBusiness1;
    }

    public void setAboutBusiness1(String aboutBusiness1) {
        this.aboutBusiness1 = aboutBusiness1;
    }

    public String getBusinessWebsite1() {
        return businessWebsite1;
    }

    public void setBusinessWebsite1(String businessWebsite1) {
        this.businessWebsite1 = businessWebsite1;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

}
