package com.spand0x.cardealer.models.dtos;

import com.google.gson.annotations.Expose;
import com.spand0x.cardealer.models.entities.Car;

public class SaleDto {
    @Expose
    private double discount;
    @Expose
    private String carMake;
    @Expose
    private String carModel;

    public SaleDto() {
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
