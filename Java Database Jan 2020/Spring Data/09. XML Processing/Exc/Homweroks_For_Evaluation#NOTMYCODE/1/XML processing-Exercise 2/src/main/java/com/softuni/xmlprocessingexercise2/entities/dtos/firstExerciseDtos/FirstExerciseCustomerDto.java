package com.softuni.xmlprocessingexercise2.entities.dtos.firstExerciseDtos;


import com.softuni.xmlprocessingexercise2.config.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstExerciseCustomerDto {

    @XmlElement(name="id")
    private long id;

    @XmlElement(name="name")
    private String name;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name="birth-date")
    private LocalDate birthDate;

    @XmlElement(name="is-young-driver")
    private boolean isYoungDriver;
}
