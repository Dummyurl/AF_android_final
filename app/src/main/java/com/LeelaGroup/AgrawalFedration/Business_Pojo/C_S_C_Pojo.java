package com.LeelaGroup.AgrawalFedration.Business_Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 24-07-2017.
 */

public class C_S_C_Pojo {
    @SerializedName("cname")
    String cname;

    @SerializedName("city_name")
    String city_name;

    @SerializedName("sname")
    String sname;

    public String getCname() {
        return cname;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getSname() {
        return sname;
    }



}
