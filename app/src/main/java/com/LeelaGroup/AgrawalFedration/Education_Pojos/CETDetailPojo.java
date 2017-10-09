package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 08-07-2017.
 */

public class CETDetailPojo {


    @SerializedName("exam")

    String exam;

    @SerializedName("sess")
    String sess;

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


    public String getCet_get_facility() {
        return cet_get_facility;
    }

    public void setCet_get_facility(String cet_get_facility) {
        this.cet_get_facility = cet_get_facility;
    }

    public String getCd_cm_from() {
        return cd_cm_from;
    }

    public void setCd_cm_from(String cd_cm_from) {
        this.cd_cm_from = cd_cm_from;
    }

    public String getCd_lang() {
        return cd_lang;
    }

    public void setCd_lang(String cd_lang) {
        this.cd_lang = cd_lang;
    }

    public String getCd_schedule() {
        return cd_schedule;
    }

    public void setCd_schedule(String cd_schedule) {
        this.cd_schedule = cd_schedule;
    }

    public String getCd_exam_center() {
        return cd_exam_center;
    }

    public void setCd_exam_center(String cd_exam_center) {
        this.cd_exam_center = cd_exam_center;
    }

    @SerializedName("cet_get_facility")
    String cet_get_facility;

    @SerializedName("cd_cm_from")
    String cd_cm_from;

    @SerializedName("cd_lang")
    String cd_lang;

    @SerializedName("cd_schedule")
    String cd_schedule;

    @SerializedName("cd_exam_center")
    String cd_exam_center;
}
