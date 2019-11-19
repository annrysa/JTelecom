package com.jtelecom.entities.loyalty;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_loyalty")
public class UserLoyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "loyalty_id")
    private int loyaltyId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "loyalty_code")
    private String loyaltyCode;

    @Column(name = "due_date_active")
    private String dueDateActive;

    @Column(name = "is_active")
    private Integer isActive;


    public UserLoyalty() {
    }

    public UserLoyalty(int loyaltyId, int userId, String loyaltyCode, String dueDateActive, Integer isActive) {
        this.loyaltyId = loyaltyId;
        this.userId = userId;
        this.loyaltyCode = loyaltyCode;
        this.dueDateActive = dueDateActive;
        this.isActive = isActive;
    }

    public int getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(int loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoyaltyCode() {
        return loyaltyCode;
    }

    public void setLoyaltyCode(String loyaltyCode) {
        this.loyaltyCode = loyaltyCode;
    }

    public String getDueDateActive() {
        return dueDateActive;
    }

    public void setDueDateActive(String dueDateActive) {
        this.dueDateActive = dueDateActive;
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
