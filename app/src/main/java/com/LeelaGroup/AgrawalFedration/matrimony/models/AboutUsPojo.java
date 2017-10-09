package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 26-07-2017.
 */

public class AboutUsPojo {
    @SerializedName("mat_abt_name")
    @Expose
    private String matAbtName;
    @SerializedName("mat_abt_desc")
    @Expose
    private String matAbtDesc;
    @SerializedName("mat_abt_conc_desc")
    @Expose
    private String matAbtConcDesc;
    @SerializedName("mat_abt_need_desc")
    @Expose
    private String matAbtNeedDesc;

    public String getMatAbtName() {
        return matAbtName;
    }

    public void setMatAbtName(String matAbtName) {
        this.matAbtName = matAbtName;
    }

    public String getMatAbtDesc() {
        return matAbtDesc;
    }

    public void setMatAbtDesc(String matAbtDesc) {
        this.matAbtDesc = matAbtDesc;
    }

    public String getMatAbtConcDesc() {
        return matAbtConcDesc;
    }

    public void setMatAbtConcDesc(String matAbtConcDesc) {
        this.matAbtConcDesc = matAbtConcDesc;
    }

    public String getMatAbtNeedDesc() {
        return matAbtNeedDesc;
    }

    public void setMatAbtNeedDesc(String matAbtNeedDesc) {
        this.matAbtNeedDesc = matAbtNeedDesc;
    }
}
