package com.example.xmlprocessingdemo.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {

    @XmlElement(name = "make")
    private String make;

    @XmlElement(name = "model")
    private String model;

    @XmlElement(name = "travelled-distance")
    private Integer travelledDistance;

    public CarSeedDto() {
    }


    @NotNull(message = "Car name can not be null!")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Min(value = 0,message = "Distance can not be negative")
    public Integer getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Integer travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
