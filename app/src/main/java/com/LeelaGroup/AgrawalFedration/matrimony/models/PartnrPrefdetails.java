package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 15-07-2017.
 */

public class PartnrPrefdetails {


    @SerializedName("mreg_looking_for")
    @Expose
    private String mregLookingFor;
    @SerializedName("mreg_reg_maxage")
    @Expose
    private String mregRegMaxage;
    @SerializedName("mreg_reg_minage")
    @Expose
    private String mregRegMinage;
    @SerializedName("mreg_country_leaving")
    @Expose
    private String mregCountryLeaving;
    @SerializedName("mreg_state_leaving")
    @Expose
    private String mregStateLeaving;
    @SerializedName("mreg_city_leaving")
    @Expose
    private String mregCityLeaving;
    @SerializedName("mreg_residential_status")
    @Expose
    private String mregResidentialStatus;
    @SerializedName("mreg_max_ht")
    @Expose
    private String mregMaxHt;
    @SerializedName("mreg_min_ht")
    @Expose
    private String mregMinHt;
    @SerializedName("mreg_groom_complexion")
    @Expose
    private String mregGroomComplexion;
    @SerializedName("mreg_educ")
    @Expose
    private String mregEduc;
    @SerializedName("mreg_religion")
    @Expose
    private String mregReligion;
    @SerializedName("mreg_caste")
    @Expose
    private String mregCaste;

    public String getMregLookingFor() {
        return mregLookingFor;
    }

    public void setMregLookingFor(String mregLookingFor) {
        this.mregLookingFor = mregLookingFor;
    }

    public String getMregRegMaxage() {
        return mregRegMaxage;
    }

    public void setMregRegMaxage(String mregRegMaxage) {
        this.mregRegMaxage = mregRegMaxage;
    }

    public String getMregRegMinage() {
        return mregRegMinage;
    }

    public void setMregRegMinage(String mregRegMinage) {
        this.mregRegMinage = mregRegMinage;
    }

    public String getMregCountryLeaving() {
        return mregCountryLeaving;
    }

    public void setMregCountryLeaving(String mregCountryLeaving) {
        this.mregCountryLeaving = mregCountryLeaving;
    }

    public String getMregStateLeaving() {
        return mregStateLeaving;
    }

    public void setMregStateLeaving(String mregStateLeaving) {
        this.mregStateLeaving = mregStateLeaving;
    }

    public String getMregCityLeaving() {
        return mregCityLeaving;
    }

    public void setMregCityLeaving(String mregCityLeaving) {
        this.mregCityLeaving = mregCityLeaving;
    }

    public String getMregResidentialStatus() {
        return mregResidentialStatus;
    }

    public void setMregResidentialStatus(String mregResidentialStatus) {
        this.mregResidentialStatus = mregResidentialStatus;
    }

    public String getMregMaxHt() {
        return mregMaxHt;
    }

    public void setMregMaxHt(String mregMaxHt) {
        this.mregMaxHt = mregMaxHt;
    }

    public String getMregMinHt() {
        return mregMinHt;
    }

    public void setMregMinHt(String mregMinHt) {
        this.mregMinHt = mregMinHt;
    }

    public String getMregGroomComplexion() {
        return mregGroomComplexion;
    }

    public void setMregGroomComplexion(String mregGroomComplexion) {
        this.mregGroomComplexion = mregGroomComplexion;
    }

    public String getMregEduc() {
        return mregEduc;
    }

    public void setMregEduc(String mregEduc) {
        this.mregEduc = mregEduc;
    }

    public String getMregReligion() {
        return mregReligion;
    }

    public void setMregReligion(String mregReligion) {
        this.mregReligion = mregReligion;
    }

    public String getMregCaste() {
        return mregCaste;
    }

    public void setMregCaste(String mregCaste) {
        this.mregCaste = mregCaste;
    }

}
