package com.softuni.xmlprocessingexercise2.entities.dtos.firstExerciseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstExerciseRootDto {

    @XmlElement(name="customer")
    List<FirstExerciseCustomerDto>customers;
}
