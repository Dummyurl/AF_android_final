package com.LeelaGroup.AgrawalFedration.Education_Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 11-07-2017.
 */

public class ServerResponse {



    boolean success;

    String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
