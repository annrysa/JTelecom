package com.jtelecom.entities.services;

import com.jtelecom.entities.addsOn.UserServiceCalls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_calls")
public class ServiceCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "activation_cost")
    private Integer activationCost;

    @Column(name = "validity")
    private String validity;

    @Column(name = "calls_min_abroad")
    private String callsMinAbroad;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private Set<UserServiceCalls> userServiceCalls;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Integer getActivationCost() {
        return activationCost;
    }

    public void setActivationCost(Integer activationCost) {
        this.activationCost = activationCost;
    }

    public String getCallsMinAbroad() {
        return callsMinAbroad;
    }

    public void setCallsMinAbroad(String callsMinAbroad) {
        this.callsMinAbroad = callsMinAbroad;
    }

    @Override
    public String toString() {
        return "ServiceCalls{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", activationCost=" + activationCost +
                ", validity=" + validity +
                ", callsMinAbroad='" + callsMinAbroad + '\'' +
                '}';
    }

    public Set<UserServiceCalls> getUserServiceCalls() {
        return userServiceCalls;
    }

    public void setUserServiceCalls(Set<UserServiceCalls> userServiceCalls) {
        this.userServiceCalls = userServiceCalls;
    }
}
