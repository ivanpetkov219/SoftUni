package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;


public class CarSeedDto {

    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private String travelledDistance;

    public CarSeedDto() {
    }


    @NotNull(message = "Make can not be null!")
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

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}