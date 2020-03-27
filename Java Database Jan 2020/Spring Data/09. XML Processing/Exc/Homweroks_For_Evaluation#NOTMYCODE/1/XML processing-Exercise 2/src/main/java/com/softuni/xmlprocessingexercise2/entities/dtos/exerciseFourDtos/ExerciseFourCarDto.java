package com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFourDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExerciseFourCarDto {

    @XmlAttribute(name="make")
    private String make;

    @XmlAttribute(name="model")
    private String model;

    @XmlAttribute(name="travelled-distance")
    private long travelledDistance;

    @XmlElement(name="parts")
    private ExerciseFourPartsRootDto parts;
}
