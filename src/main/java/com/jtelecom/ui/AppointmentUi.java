package com.jtelecom.ui;

public class AppointmentUi {
    private Integer homeInternetId;
    private String dateTime;

    public AppointmentUi() {
    }

    public AppointmentUi(Integer homeInternetId, String dateTime) {
        this.homeInternetId = homeInternetId;
        this.dateTime = dateTime;
    }

    public Integer getHomeInternetId() {
        return homeInternetId;
    }

    public void setHomeInternetId(Integer homeInternetId) {
        this.homeInternetId = homeInternetId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
