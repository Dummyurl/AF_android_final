package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 10-07-2017.
 */

public class OtherDetailPojo {


    @SerializedName("exam")
    String exam;

    @SerializedName("sess")
    String sess;


    @SerializedName("other_activity")
    String other_activity;

    @SerializedName("other_national_level")
    String other_national_level;

    @SerializedName("other_year")
    String other_year;

    @SerializedName("other_pos")
    String other_pos;

    @SerializedName("other_achieve")
    String other_achieve;

    @SerializedName("other_hobbies")
    String other_hobbies;

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    public String getOther_activity() {
        return other_activity;
    }

    public void setOther_activity(String other_activity) {
        this.other_activity = other_activity;
    }

    public String getOther_national_level() {
        return other_national_level;
    }

    public void setOther_national_level(String other_national_level) {
        this.other_national_level = other_national_level;
    }

    public String getOther_year() {
        return other_year;
    }

    public void setOther_year(String other_year) {
        this.other_year = other_year;
    }

    public String getOther_pos() {
        return other_pos;
    }

    public void setOther_pos(String other_pos) {
        this.other_pos = other_pos;
    }

    public String getOther_achieve() {
        return other_achieve;
    }

    public void setOther_achieve(String other_achieve) {
        this.other_achieve = other_achieve;
    }

    public String getOther_hobbies() {
        return other_hobbies;
    }

    public void setOther_hobbies(String other_hobbies) {
        this.other_hobbies = other_hobbies;
    }
}
