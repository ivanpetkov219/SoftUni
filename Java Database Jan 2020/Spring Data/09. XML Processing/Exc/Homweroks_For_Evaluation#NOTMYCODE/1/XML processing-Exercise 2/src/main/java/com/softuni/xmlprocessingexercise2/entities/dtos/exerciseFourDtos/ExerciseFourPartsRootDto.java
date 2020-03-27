package com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFourDtos;

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
@XmlRootElement(name="parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExerciseFourPartsRootDto {

    @XmlElement(name="part")
    private List<ExerciseFourPartDto> parts;
}
