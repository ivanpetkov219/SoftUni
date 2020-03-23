package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;


public class PartSeedDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer quantity;

    public PartSeedDto() {
    }
    @NotNull(message = "Name cannot be null.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
