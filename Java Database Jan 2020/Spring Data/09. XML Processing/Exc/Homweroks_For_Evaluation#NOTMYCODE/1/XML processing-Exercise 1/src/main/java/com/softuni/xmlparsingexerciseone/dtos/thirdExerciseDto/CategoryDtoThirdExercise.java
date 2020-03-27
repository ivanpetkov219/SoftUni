package com.softuni.xmlparsingexerciseone.dtos.thirdExerciseDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDtoThirdExercise {

    @XmlAttribute(name="category-name")
    private String categoryName;

    @XmlElement(name="products-count")
    private int productCount;

    @XmlElement(name="average-price")
    private double averagePrice;

    @XmlElement(name="total-revenue")
    private double totalAvenue;
}
