package com.LeelaGroup.AgrawalFedration.Business_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 16-07-2017.
 */

public class BusinessCardPojo {
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("Website")
    @Expose
    private String website;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
