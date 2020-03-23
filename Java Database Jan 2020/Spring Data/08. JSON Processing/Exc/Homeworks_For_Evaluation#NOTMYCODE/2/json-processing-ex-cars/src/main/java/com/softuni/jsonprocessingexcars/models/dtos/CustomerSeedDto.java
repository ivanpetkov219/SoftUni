package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;
import com.softuni.jsonprocessingexcars.adaptors.LocalDateTimeAdapter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;


public class CustomerSeedDto {
    @Expose
    private String name;
    @Expose
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthdate;
    @Expose
    private Boolean isYoungDriver;

    public CustomerSeedDto() {
    }
    @NotNull(message = "Name cannot be null!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
