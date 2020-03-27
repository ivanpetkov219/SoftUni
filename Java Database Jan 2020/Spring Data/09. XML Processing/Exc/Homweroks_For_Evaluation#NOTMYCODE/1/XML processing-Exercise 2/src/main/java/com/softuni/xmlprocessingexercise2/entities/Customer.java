package com.softuni.xmlprocessingexercise2.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter

public class Customer extends BaseEntity {
    public Customer() {
    }

    public Customer(String name, LocalDate birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public Customer(int id, String name, LocalDate birthDate, boolean isYoungDriver) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    @Column
    private String name;

    @Column(name="birth_date")
    private LocalDate birthDate;


    @Column(name="is_young_driver")
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Set<Sale> sales;


}
