package com.softuni.xmlprocessingexercise2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Car extends BaseEntity {

    @Column
    private String make;

    @Column
    private String model;

    @Column(name="travelled_distance")
    private long travelledDistance;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cars_parts",
            joinColumns = { @JoinColumn(name = "car_id",referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "part_id",referencedColumnName = "id") }
    )
    private List<Part> parts;

    @OneToOne(mappedBy = "car")
    private Sale sale;
}
