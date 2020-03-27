package com.softuni.xmlprocessingexercise2.entities.dtos.importDtos;

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
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {

    @XmlElement(name="make")
    private String make;
    @XmlElement(name="model")
    private String model;
    @XmlElement(name="travelled-distance")
    private long travelledDistance;

}
