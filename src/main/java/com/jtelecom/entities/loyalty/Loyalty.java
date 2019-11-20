package com.jtelecom.entities.loyalty;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "loyalty")
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loyalty_id")
    private int loyaltyId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Integer amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loyalty_id", referencedColumnName = "loyalty_id")
    private UserLoyalty userLoyalty;


    public Loyalty() {
    }

    public int getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(int loyaltyId) {
        this.loyaltyId = loyaltyId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UserLoyalty getUserLoyalty() {
        return userLoyalty;
    }

    public void setUserLoyalty(UserLoyalty userLoyalty) {
        this.userLoyalty = userLoyalty;
    }
}
