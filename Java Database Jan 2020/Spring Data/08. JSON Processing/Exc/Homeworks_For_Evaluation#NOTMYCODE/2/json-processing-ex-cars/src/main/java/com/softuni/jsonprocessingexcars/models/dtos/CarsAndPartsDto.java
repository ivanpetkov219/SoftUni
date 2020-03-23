package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarsAndPartsDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private String travelledDistance;
    @Expose
    private List<PartsViewDto> parts;

    public CarsAndPartsDto() {
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

    public List<PartsViewDto> getParts() {
        return parts;
    }

    public void setParts(List<PartsViewDto> parts) {
        this.parts = parts;
    }
}
