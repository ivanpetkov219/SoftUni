package com.softuni.xmlprocessingexercise2.entities.dtos.importDtos;

import com.softuni.xmlprocessingexercise2.config.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersImportDto {

    @XmlAttribute(name="name")
    private String name;

    @XmlElement(name="birth-date")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate birthDate;

    @XmlElement(name="is-young-driver")
    private boolean isYoungDriver;
}
