package com.softuni.xmlprocessingexercise2.entities.dtos.secondExerciseDtos;

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
@XmlRootElement(name="car")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecondExerciseDto {

    @XmlAttribute(name="id")
    private long id;

    @XmlAttribute(name="make")
    private String make;

    @XmlAttribute(name="model")
    private String model;

    @XmlAttribute(name="travelled-distance")
    private long travelledDistance;

}
