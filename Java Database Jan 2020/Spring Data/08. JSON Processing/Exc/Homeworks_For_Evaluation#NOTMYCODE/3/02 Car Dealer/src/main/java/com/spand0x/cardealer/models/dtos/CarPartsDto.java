package com.spand0x.cardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarPartsDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private long travelledDistance;
    @Expose
    private List<PartNamePriceDto> parts;

    public CarPartsDto() {
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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartNamePriceDto> getParts() {
        return parts;
    }

    public void setParts(List<PartNamePriceDto> parts) {
        this.parts = parts;
    }
}
