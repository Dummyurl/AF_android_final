package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 16-07-2017.
 */

public class LoginModel {
    @SerializedName("mat_fname")
    @Expose
    private String matFname;

    @SerializedName("mat_lname")
    @Expose
    private String matLname;

    public String getMatLname() {
        return matLname;
    }

    public void setMatLname(String matLname) {
        this.matLname = matLname;
    }

    public String getMatFname() {
        return matFname;
    }


    @SerializedName("mreg_prof_pic")
    @Expose
    private String mregProfPic;

    public String getMregProfPic() {
        return mregProfPic;
    }

    public void setMregProfPic(String mregProfPic) {
        this.mregProfPic = mregProfPic;
    }

    public void setMatFname(String matFname) {
        this.matFname = matFname;
    }

    @SerializedName("mat_id")
    @Expose
    private String matId;
    @SerializedName("mat_email")
    @Expose
    private String matEmail;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMatId() {
        return matId;
    }

    public void setMatId(String matId) {
        this.matId = matId;
    }

    public String getMatEmail() {
        return matEmail;
    }

    public void setMatEmail(String matEmail) {
        this.matEmail = matEmail;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
