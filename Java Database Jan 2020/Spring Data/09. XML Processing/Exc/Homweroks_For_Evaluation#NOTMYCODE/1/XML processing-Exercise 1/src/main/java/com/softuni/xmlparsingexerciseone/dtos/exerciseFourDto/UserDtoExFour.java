package com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDtoExFour {

    @XmlAttribute(name="first-name")
    private String firstName;

    @XmlAttribute(name="last-name")
    private String lastName;

    @XmlAttribute(name="age")
    private int age;

    @XmlElement(name="sold-products")
    private SoldProductsRootExFour sold;




}
