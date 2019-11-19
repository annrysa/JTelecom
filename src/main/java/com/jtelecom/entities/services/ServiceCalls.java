package com.jtelecom.entities.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_calls")
public class ServiceCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

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

    @Column(name = "calls_min_abroad")
    private String callsMinAbroad;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
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

    public Integer getActivationCost() {
        return activationCost;
    }

    public void setActivationCost(Integer activationCost) {
        this.activationCost = activationCost;
    }

    public String getCallsMinAbroad() {
        return callsMinAbroad;
    }

    public void setCallsMinAbroad(String callsMinAbroad) {
        this.callsMinAbroad = callsMinAbroad;
    }

    @Override
    public String toString() {
        return "ServiceCalls{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", activationCost=" + activationCost +
                ", validity=" + validity +
                ", callsMinAbroad='" + callsMinAbroad + '\'' +
                '}';
    }
}
