package com.jtelecom.ui;

public class AddsOnUiModel {

    private Integer serviceId;
    private String serviceType;
    private String name;

    public AddsOnUiModel() {
    }

    public AddsOnUiModel(Integer serviceId, String serviceType, String name) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.name = name;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddsOnType getAddsOnType() {
        return AddsOnType.valueOf(getServiceType());
    }
}
