package com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsRootExFour {

    @XmlAttribute(name="count")
    private int count;

    @XmlElement(name="product")
    private List<ProductsDtoExFour> sold;
}
