package com.softuni.xmlparsingexerciseone.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Table(name="categories")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {

    @Column
    @Length(min=3,max=15,message = "The length should be between 3 and 15")
    private String name;

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Product>products;
}
