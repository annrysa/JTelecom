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
    private int id;

    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "user_id")
    private int userId;

    public UserServiceInternet() {
        super();
    }

    public UserServiceInternet(int serviceId, int userId) {
        this.serviceId = serviceId;
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
