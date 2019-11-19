package com.jtelecom.entities.homeInternet;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "home_internet")
public class HomeInternet {

    @Id
    @Column(name = "home_internet_id")
    private int homeInternetId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "internet")
    private String internet;

    @Column(name = "internet_unlimited")
    private Integer internetUnlimited;

    public HomeInternet() {
    }

    public int getHomeInternetId() {
        return homeInternetId;
    }

    public void setHomeInternetId(int homeInternetId) {
        this.homeInternetId = homeInternetId;
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
}
