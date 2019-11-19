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
    private int id;

    @Column(name = "home_internet_id")
    private int homeInternetId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "appointment")
    private String appointment;

    @Column(name = "is_active")
    private Integer isActive;

    public UserHomeInternet() {
    }

    public UserHomeInternet(int homeInternetId, int userId, String appointment, Integer isActive) {
        this.homeInternetId = homeInternetId;
        this.userId = userId;
        this.appointment = appointment;
        this.isActive = isActive;
    }

    public int getHomeInternetId() {
        return homeInternetId;
    }

    public void setHomeInternetId(int homeInternetId) {
        this.homeInternetId = homeInternetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
