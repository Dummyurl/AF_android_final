package com.LeelaGroup.AgrawalFedration.matrimony.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USer on 25-07-2017.
 */

public class EventsDetails {
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("event_time")
    @Expose
    private String eventTime;

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @SerializedName("event_desc")
    @Expose
    private String eventDesc;
    @SerializedName("event_location")
    @Expose
    private String eventLocation;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("event_addr")
    @Expose
    private String eventAddr;
    @SerializedName("event_patrika")
    @Expose
    private String eventPatrika;
    @SerializedName("event_wed_pic")
    @Expose
    private String eventWedPic;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventAddr() {
        return eventAddr;
    }

    public void setEventAddr(String eventAddr) {
        this.eventAddr = eventAddr;
    }

    public String getEventPatrika() {
        return eventPatrika;
    }

    public void setEventPatrika(String eventPatrika) {
        this.eventPatrika = eventPatrika;
    }

    public String getEventWedPic() {
        return eventWedPic;
    }

    public void setEventWedPic(String eventWedPic) {
        this.eventWedPic = eventWedPic;
    }
}
