package com.jtelecom.ui;

public enum AddsOnType {
    INTERNET("internet"),
    ROAMING("roaming"),
    CALLS("calls");
    String type;

    AddsOnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
