package com.jtelecom.entities.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_internet")
public class ServiceInternet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "activation_cost")
    private Integer activationCost;

    @Column(name = "validity")
    private String validity;

    @Column(name = "internet")
    private String internet;

    @Column(name = "internet_sharing")
    private Integer internetSharing;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public Integer getActivationCost() {
        return activationCost;
    }

    public void setActivationCost(Integer activationCost) {
        this.activationCost = activationCost;
    }

    public Integer getInternetSharing() {
        return internetSharing;
    }

    public void setInternetSharing(Integer internetSharing) {
        this.internetSharing = internetSharing;
    }
}
