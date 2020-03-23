package com.softuni.jsonprocessingexcars.models.dtos;

import com.google.gson.annotations.Expose;

public class LocalSuppliersDto {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private int partsCount;

    public LocalSuppliersDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
