package com.jtelecom.entities.tariff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tariff")
public class UserTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "tariff_id")
    private Integer tariffId;

//    @ManyToOne
//    @JoinColumn(name="tariff_id")
//    private Tariff tariff;

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

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Tariff getTariff() {
//        return tariff;
//    }
//
//    public void setTariff(Tariff tariff) {
//        this.tariff = tariff;
//    }
}
