package com.jtelecom.entities.addsOn;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_service_calls")
public class UserServiceCalls extends UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_active")
    private Integer isActive;

    public UserServiceCalls() {
        super();
    }

    public UserServiceCalls(int serviceId, int userId) {
        this.serviceId = serviceId;
        this.userId = userId;
    }

    public UserServiceCalls(Integer serviceId, Integer userId, Integer isActive) {
        this.serviceId = serviceId;
        this.userId = userId;
        this.isActive = isActive;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
