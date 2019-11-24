package com.jtelecom.entities.homeInternet;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_home_internet")
public class UserHomeInternet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "home_internet_id")
    private Integer homeInternetId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "appointment")
    private String appointment;

    @Column(name = "is_active")
    private Integer isActive;

    public UserHomeInternet() {
    }

    public UserHomeInternet(Integer homeInternetId, int userId, String appointment, Integer isActive) {
        this.homeInternetId = homeInternetId;
        this.userId = userId;
        this.appointment = appointment;
        this.isActive = isActive;
    }

    public Integer getHomeInternetId() {
        return homeInternetId;
    }

    public void setHomeInternetId(Integer homeInternetId) {
        this.homeInternetId = homeInternetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
