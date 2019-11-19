package com.jtelecom.ui;

public enum OrderAction {
    ACTIVATED("Activated"),
    DEACTIVATED("Deactivated");
    String action;

    OrderAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
