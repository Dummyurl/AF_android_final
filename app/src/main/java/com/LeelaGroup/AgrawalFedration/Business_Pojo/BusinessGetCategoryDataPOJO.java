package com.LeelaGroup.AgrawalFedration.Business_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 19-07-2017.
 */

public class BusinessGetCategoryDataPOJO {

    @SerializedName("cat_name")
    @Expose
    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
