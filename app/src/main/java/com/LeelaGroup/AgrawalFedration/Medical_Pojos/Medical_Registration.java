package com.LeelaGroup.AgrawalFedration.Medical_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 06-07-2017.
 */

public class Medical_Registration {


    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("user_id")
    String user_id ;

    @SerializedName("user_fname")
    String user_fname;

    @SerializedName("user_email")
    String user_email;

    @SerializedName("user_phone")
    String user_phone;

    @SerializedName("user_pwd")
    String user_pwd;

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
