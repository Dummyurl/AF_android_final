package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 13-07-2017.
 */

public class EducationLoginPojo {


    boolean success;

    String message;

    @SerializedName("pd_email")
    String pd_email;

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

    public String getPd_email() {
        return pd_email;
    }

    public void setPd_email(String pd_email) {
        this.pd_email = pd_email;
    }

    public String getPd_pwd() {
        return pd_pwd;
    }

    public void setPd_pwd(String pd_pwd) {
        this.pd_pwd = pd_pwd;
    }

}
