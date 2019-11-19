package com.jtelecom.ui;

public enum OrderType {
    HOME_INTERNET("home Internet"),
    LOYALTY("bonus"),
    TARIFF("tariff"),
    SERVICE("service");
    String type;

    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
