package com.softuni.xmlparsingexerciseone.dtos.secondExerciseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecondExerciseProductDto {

    @XmlElement(name="name")
    private String productName;
    @XmlElement(name="price")
    private double price;

    @XmlElement(name="buyer-first-name")
    private String buyerFirstName;

    @XmlElement(name="buyer-last-name")
    private String buyerLastName;

}
