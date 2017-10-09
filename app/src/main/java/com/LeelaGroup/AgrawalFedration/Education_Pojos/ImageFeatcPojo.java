package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 20-07-2017.
 */

public class ImageFeatcPojo {


    @SerializedName("ed_prof_other_cert")
    @Expose
    private String edProfOtherCert;

    @SerializedName("ed_prof_ssc_marksheet")
    @Expose
    private String edProfSscMarksheet;
    @SerializedName("ed_prof_hsc_marksheet ")
    @Expose
    private String edProfHscMarksheet;
    @SerializedName("ed_prof_gd_marksheet ")
    @Expose
    private String edProfGdMarksheet;
    @SerializedName("ed_prof_pg_marksheet ")
    @Expose
    private String edProfPgMarksheet;
    @SerializedName("ed_prof_scan_sign")
    @Expose
    private String edProfScanSign;

    public String getEdProfSscMarksheet() {
        return edProfSscMarksheet;
    }

    public void setEdProfSscMarksheet(String edProfSscMarksheet) {
        this.edProfSscMarksheet = edProfSscMarksheet;
    }

    public String getEdProfHscMarksheet() {
        return edProfHscMarksheet;
    }

    public void setEdProfHscMarksheet(String edProfHscMarksheet) {
        this.edProfHscMarksheet = edProfHscMarksheet;
    }

    public String getEdProfGdMarksheet() {
        return edProfGdMarksheet;
    }

    public void setEdProfGdMarksheet(String edProfGdMarksheet) {
        this.edProfGdMarksheet = edProfGdMarksheet;
    }

    public String getEdProfPgMarksheet() {
        return edProfPgMarksheet;
    }

    public void setEdProfPgMarksheet(String edProfPgMarksheet) {
        this.edProfPgMarksheet = edProfPgMarksheet;
    }

    public String getEdProfScanSign() {
        return edProfScanSign;
    }

    public void setEdProfScanSign(String edProfScanSign) {
        this.edProfScanSign = edProfScanSign;
    }

    public String getEdProfOtherCert() {
        return edProfOtherCert;
    }

    public void setEdProfOtherCert(String edProfOtherCert) {
        this.edProfOtherCert = edProfOtherCert;
    }

}
