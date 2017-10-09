package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 27-07-2017.
 */

public class CountryStateCity {

    @SerializedName("cname")
    @Expose
    private String country;

    @SerializedName("sname")
    @Expose
    private String state;

    @SerializedName("city_name")
    @Expose
    private String city;


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


}
