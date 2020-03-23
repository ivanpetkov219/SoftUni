package com.softuni.jsonprocessingex.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SoldProductsDto {
    @Expose
    private Integer count;
    @Expose
    private List<ProductViewDto> products;

    public SoldProductsDto() {
        this.products=new ArrayList<>();
    }

    public SoldProductsDto(Integer count, List<ProductViewDto> products) {
        this.count = count;
        this.products = products;
    }
}