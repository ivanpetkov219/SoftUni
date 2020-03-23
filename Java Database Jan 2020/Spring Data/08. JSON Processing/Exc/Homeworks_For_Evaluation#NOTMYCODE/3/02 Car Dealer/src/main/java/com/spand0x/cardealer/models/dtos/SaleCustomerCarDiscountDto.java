package com.spand0x.cardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleCustomerCarDiscountDto {
    @Expose
    private CarBasicDto car;
    @Expose
    private String customerName;
    @Expose
    private double discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SaleCustomerCarDiscountDto() {
    }

    public CarBasicDto getCar() {
        return car;
    }

    public void setCar(CarBasicDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
