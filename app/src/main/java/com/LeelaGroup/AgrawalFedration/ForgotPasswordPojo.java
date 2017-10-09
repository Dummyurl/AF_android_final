package com.LeelaGroup.AgrawalFedration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 04-08-2017.
 */

public class ForgotPasswordPojo {


    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
