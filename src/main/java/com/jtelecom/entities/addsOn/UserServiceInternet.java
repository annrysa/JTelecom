package com.jtelecom.entities.addsOn;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_service_internet")
public class UserServiceInternet extends UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "user_id")
    private Integer userId;

    public UserServiceInternet() {
        super();
    }

    public UserServiceInternet(Integer serviceId, Integer userId) {
        this.serviceId = serviceId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
