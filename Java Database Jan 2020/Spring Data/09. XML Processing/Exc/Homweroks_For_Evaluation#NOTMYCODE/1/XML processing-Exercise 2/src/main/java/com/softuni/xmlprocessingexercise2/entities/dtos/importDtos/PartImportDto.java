package com.softuni.xmlprocessingexercise2.entities.dtos.importDtos;

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
@XmlRootElement(name="part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDto {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="price")
    private double price;

    @XmlAttribute(name="quantity")
    private int quantity;
}
