package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 26-07-2017.
 */

public class SussessStoriesPojo {

    @SerializedName("mat_suc_wed_pic")
    @Expose
    private String matSucWedPic;
    @SerializedName("mat_suc_groom_name")
    @Expose
    private String matSucGroomName;
    @SerializedName("mat_suc_bride_name")
    @Expose
    private String matSucBrideName;
    @SerializedName("mat_suc_bride_pic")
    @Expose
    private String matSucBridePic;
    @SerializedName("mat_suc_groom_pic")
    @Expose
    private String matSucGroomPic;
    @SerializedName("mat_suc_location")
    @Expose
    private String matSucLocation;
    @SerializedName("mat_suc_af_help")
    @Expose
    private String matSucAfHelp;
    @SerializedName("mat_suc_wed_date")
    @Expose
    private String matSucWedDate;

    public String getMatSucWedPic() {
        return matSucWedPic;
    }

    public void setMatSucWedPic(String matSucWedPic) {
        this.matSucWedPic = matSucWedPic;
    }

    public String getMatSucGroomName() {
        return matSucGroomName;
    }

    public void setMatSucGroomName(String matSucGroomName) {
        this.matSucGroomName = matSucGroomName;
    }

    public String getMatSucBrideName() {
        return matSucBrideName;
    }

    public void setMatSucBrideName(String matSucBrideName) {
        this.matSucBrideName = matSucBrideName;
    }

    public String getMatSucBridePic() {
        return matSucBridePic;
    }

    public void setMatSucBridePic(String matSucBridePic) {
        this.matSucBridePic = matSucBridePic;
    }

    public String getMatSucGroomPic() {
        return matSucGroomPic;
    }

    public void setMatSucGroomPic(String matSucGroomPic) {
        this.matSucGroomPic = matSucGroomPic;
    }

    public String getMatSucLocation() {
        return matSucLocation;
    }

    public void setMatSucLocation(String matSucLocation) {
        this.matSucLocation = matSucLocation;
    }

    public String getMatSucAfHelp() {
        return matSucAfHelp;
    }

    public void setMatSucAfHelp(String matSucAfHelp) {
        this.matSucAfHelp = matSucAfHelp;
    }

    public String getMatSucWedDate() {
        return matSucWedDate;
    }

    public void setMatSucWedDate(String matSucWedDate) {
        this.matSucWedDate = matSucWedDate;
    }
}
