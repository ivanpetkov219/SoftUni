package com.softuni.xmlprocessingexercise2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier  extends  BaseEntity{

    @Column
    private String name;

    @Column(name="is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier",targetEntity = Part.class,cascade = CascadeType.PERSIST)
    private Set<Part> parts;


}
