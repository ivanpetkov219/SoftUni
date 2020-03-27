package com.softuni.xmlparsingexerciseone.dtos.firstExerciseDtos;


import com.sun.xml.bind.XmlAccessorFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootProducts{


    @XmlElement(name="product")
    private Set<ProductDto> products;
}
