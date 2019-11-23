package com.jtelecom.entities.tariff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Integer tariffId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "sms")
    private String sms;
    @Column(name = "internet")
    private String internet;
    @Column(name = "internet_unlimited")
    private Integer internetUnlimited;
    @Column(name = "calls_min")
    private String callsMin;
    @Column(name = "calls_min_abroad")
    private String callsMinAbroad;
    @Column(name = "calls_min_unlimited")
    private Integer callsMinUnlimited;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tariff_id", referencedColumnName = "tariff_id")
    private UserTariff userTariff;

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public Integer getInternetUnlimited() {
        return internetUnlimited;
    }

    public void setInternetUnlimited(Integer internetUnlimited) {
        this.internetUnlimited = internetUnlimited;
    }

    public String getCallsMin() {
        return callsMin;
    }

    public void setCallsMin(String callsMin) {
        this.callsMin = callsMin;
    }

    public String getCallsMinAbroad() {
        return callsMinAbroad;
    }

    public void setCallsMinAbroad(String callsMinAbroad) {
        this.callsMinAbroad = callsMinAbroad;
    }

    public Integer getCallsMinUnlimited() {
        return callsMinUnlimited;
    }

    public void setCallsMinUnlimited(Integer callsMinUnlimited) {
        this.callsMinUnlimited = callsMinUnlimited;
    }

    public UserTariff getUserTariff() {
        return userTariff;
    }

    public void setUserTariff(UserTariff userTariff) {
        this.userTariff = userTariff;
    }
}
