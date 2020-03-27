package com.softuni.xmlprocessingexercise2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name="parts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part  extends BaseEntity{

    @Column(unique = true)
    private String name;

    @Column
    private double price;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name="supplier_id",referencedColumnName = "id")
    private Supplier supplier;



    @ManyToMany(mappedBy = "parts")
    private List<Car> cars;
}
