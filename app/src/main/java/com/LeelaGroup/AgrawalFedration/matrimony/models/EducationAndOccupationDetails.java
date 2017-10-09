package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 11-07-2017.
 */

public class EducationAndOccupationDetails {

    @SerializedName("mat_reg_edu")
    String mat_reg_edu;
    @SerializedName("mat_reg_pg")
    String mat_reg_pg;
    @SerializedName("mat_reg_docto")
    String mat_reg_docto;
    @SerializedName("mat_reg_certifi")
    String mat_reg_certifi;
    @SerializedName("mat_reg_occup")
    String mat_reg_occup;
    @SerializedName("mat_reg_industry")
    String mat_reg_industry;
    @SerializedName("mat_reg_empl")
    String mat_reg_empl;
    @SerializedName("mat_reg_ipa")
    String mat_reg_ipa;

    public String getMat_reg_edu() {
        return mat_reg_edu;
    }

    public void setMat_reg_edu(String mat_reg_edu) {
        this.mat_reg_edu = mat_reg_edu;
    }

    public String getMat_reg_pg() {
        return mat_reg_pg;
    }

    public void setMat_reg_pg(String mat_reg_pg) {
        this.mat_reg_pg = mat_reg_pg;
    }

    public String getMat_reg_docto() {
        return mat_reg_docto;
    }

    public void setMat_reg_docto(String mat_reg_docto) {
        this.mat_reg_docto = mat_reg_docto;
    }

    public String getMat_reg_certifi() {
        return mat_reg_certifi;
    }

    public void setMat_reg_certifi(String mat_reg_certifi) {
        this.mat_reg_certifi = mat_reg_certifi;
    }

    public String getMat_reg_occup() {
        return mat_reg_occup;
    }

    public void setMat_reg_occup(String mat_reg_occup) {
        this.mat_reg_occup = mat_reg_occup;
    }

    public String getMat_reg_industry() {
        return mat_reg_industry;
    }

    public void setMat_reg_industry(String mat_reg_industry) {
        this.mat_reg_industry = mat_reg_industry;
    }

    public String getMat_reg_empl() {
        return mat_reg_empl;
    }

    public void setMat_reg_empl(String mat_reg_empl) {
        this.mat_reg_empl = mat_reg_empl;
    }

    public String getMat_reg_ipa() {
        return mat_reg_ipa;
    }

    public void setMat_reg_ipa(String mat_reg_ipa) {
        this.mat_reg_ipa = mat_reg_ipa;
    }
}
