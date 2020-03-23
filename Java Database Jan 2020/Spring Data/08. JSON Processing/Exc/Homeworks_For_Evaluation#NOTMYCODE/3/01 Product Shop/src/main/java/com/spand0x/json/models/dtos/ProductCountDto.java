package com.spand0x.json.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class ProductCountDto {
    @Expose
    private int count;
    @Expose
    private Set<ProductNamePriceDto> products;

    public ProductCountDto() {
        this.products = new HashSet<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductNamePriceDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductNamePriceDto> products) {
        this.products = products;
    }
}
