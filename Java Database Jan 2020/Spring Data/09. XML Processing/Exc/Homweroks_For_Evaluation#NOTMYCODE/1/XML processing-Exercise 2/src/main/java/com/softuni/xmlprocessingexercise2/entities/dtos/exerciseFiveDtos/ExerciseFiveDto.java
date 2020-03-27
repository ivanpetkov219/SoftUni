package com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFiveDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExerciseFiveDto {

    @XmlAttribute(name="full-name")
    private String name;

    @XmlAttribute(name="bought-cars")
    private int countCars;

    @XmlAttribute(name="spent-money")
    private double spentMoney;
}
