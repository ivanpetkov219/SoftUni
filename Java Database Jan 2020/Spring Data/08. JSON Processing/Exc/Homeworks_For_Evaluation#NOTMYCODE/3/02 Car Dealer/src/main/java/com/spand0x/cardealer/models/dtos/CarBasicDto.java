package com.spand0x.cardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CarBasicDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private BigDecimal travelledDistance;

    public CarBasicDto() {
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

    public BigDecimal getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigDecimal travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
