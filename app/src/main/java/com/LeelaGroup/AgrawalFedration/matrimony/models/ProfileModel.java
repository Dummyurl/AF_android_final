package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 21-08-2017.
 */

public class ProfileModel {

    @SerializedName("mat_basic_cont_id")
    @Expose
    private String matBasicContId;
    @SerializedName("mreg_prof_pic")
    @Expose
    private String mregProfPic;
    @SerializedName("mreg_fname")
    @Expose
    private String mregFname;
    @SerializedName("mreg_lname")
    @Expose
    private String mregLname;

    public String getMatBasicContId() {
        return matBasicContId;
    }

    public void setMatBasicContId(String matBasicContId) {
        this.matBasicContId = matBasicContId;
    }

    public String getMregProfPic() {
        return mregProfPic;
    }

    public void setMregProfPic(String mregProfPic) {
        this.mregProfPic = mregProfPic;
    }

    public String getMregFname() {
        return mregFname;
    }

    public void setMregFname(String mregFname) {
        this.mregFname = mregFname;
    }

    public String getMregLname() {
        return mregLname;
    }

    public void setMregLname(String mregLname) {
        this.mregLname = mregLname;
    }
}
