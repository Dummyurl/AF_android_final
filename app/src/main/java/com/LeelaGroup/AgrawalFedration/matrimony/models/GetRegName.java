package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 31-07-2017.
 */

public class GetRegName {
    @SerializedName("mat_fname")
    @Expose
    private String matFname;
    @SerializedName("mat_lname")
    @Expose
    private String matLname;

    public String getMatFname() {
        return matFname;
    }

    public void setMatFname(String matFname) {
        this.matFname = matFname;
    }

    public String getMatLname() {
        return matLname;
    }

    public void setMatLname(String matLname) {
        this.matLname = matLname;
    }

}
