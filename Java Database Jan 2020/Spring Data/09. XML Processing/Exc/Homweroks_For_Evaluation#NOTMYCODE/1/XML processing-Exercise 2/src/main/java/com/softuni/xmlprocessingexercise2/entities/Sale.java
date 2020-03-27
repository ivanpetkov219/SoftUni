package com.softuni.xmlprocessingexercise2.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale  extends BaseEntity{

    @Column
    private double percentage;

    @OneToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;
}
