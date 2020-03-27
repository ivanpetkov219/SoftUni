package com.softuni.xmlprocessingexercise2.entities.dtos.thirdExerciseDtos;

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
@XmlRootElement(name="supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdExerciseDto {

    @XmlAttribute(name="id")
    private long id;

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="parts-count")
    private int count;
}
