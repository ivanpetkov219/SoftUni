package com.softuni.xmlparsingexerciseone.dtos.thirdExerciseDto;

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
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootCategoriesDto {


    @XmlElement(name="category")
    private List<CategoryDtoThirdExercise> categories;
}
