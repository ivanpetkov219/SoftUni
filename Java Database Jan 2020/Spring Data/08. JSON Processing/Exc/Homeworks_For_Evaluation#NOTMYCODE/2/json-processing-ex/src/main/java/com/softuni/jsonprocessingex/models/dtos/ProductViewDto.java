package com.softuni.jsonprocessingex.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductViewDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductViewDto() {
    }

    public ProductViewDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}