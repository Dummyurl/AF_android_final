package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 22-07-2017.
 */

public class FetchFilterDetail {

    @SerializedName("mreg_prof_pic")
    @Expose
    private String mregProfPic;
    @SerializedName("mat_id")
    @Expose
    private String mat_id;

    public String getMat_id() {
        return mat_id;
    }

    public void setMat_id(String mat_id) {
        this.mat_id = mat_id;
    }

    @SerializedName("name")

    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("id")
    @Expose
    private String id;

    public String getMregProfPic() {
        return mregProfPic;
    }

    public void setMregProfPic(String mregProfPic) {
        this.mregProfPic = mregProfPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
