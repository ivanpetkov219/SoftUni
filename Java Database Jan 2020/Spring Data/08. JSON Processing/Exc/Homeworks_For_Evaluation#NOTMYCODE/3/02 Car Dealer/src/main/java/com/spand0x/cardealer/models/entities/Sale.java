package com.spand0x.cardealer.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    private double discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    @Column(name = "discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}