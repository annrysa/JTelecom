package com.jtelecom.entities.tariff;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tariff")
public class UserTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "tariff_id")
    private int tariffId;


    public UserTariff() {
    }

    public UserTariff(int userId, int tariffId) {
        this.userId = userId;
        this.tariffId = tariffId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
