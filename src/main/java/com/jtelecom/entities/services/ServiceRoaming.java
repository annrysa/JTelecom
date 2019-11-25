package com.jtelecom.entities.services;

import com.jtelecom.entities.addsOn.UserServiceRoaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_roaming")
public class ServiceRoaming {

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

    @Column(name = "sms")
    private String sms;

    @Column(name = "calls_min_abroad_out")
    private String callsMinAbroadOut;

    @Column(name = "calls_min_abroad_in")
    private String callsMinAbroadIn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private Set<UserServiceRoaming> userServiceRoamings;

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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getCallsMinAbroadOut() {
        return callsMinAbroadOut;
    }

    public void setCallsMinAbroadOut(String callsMinAbroadOut) {
        this.callsMinAbroadOut = callsMinAbroadOut;
    }

    public String getCallsMinAbroadIn() {
        return callsMinAbroadIn;
    }

    public void setCallsMinAbroadIn(String callsMinAbroadIn) {
        this.callsMinAbroadIn = callsMinAbroadIn;
    }

    public Set<UserServiceRoaming> getUserServiceRoamings() {
        return userServiceRoamings;
    }

    public void setUserServiceRoamings(Set<UserServiceRoaming> userServiceRoamings) {
        this.userServiceRoamings = userServiceRoamings;
    }
}
