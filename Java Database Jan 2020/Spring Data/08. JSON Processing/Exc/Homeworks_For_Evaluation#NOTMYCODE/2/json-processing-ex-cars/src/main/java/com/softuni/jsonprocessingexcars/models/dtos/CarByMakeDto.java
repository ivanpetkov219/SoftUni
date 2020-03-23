package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;

public class CarByMakeDto {

    @Expose
    private Long id;
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private String travelledDistance;

    public CarByMakeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
