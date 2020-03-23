package com.softuni.jsonprocessingex.models.dtos;

import com.google.gson.annotations.Expose;
import com.softuni.jsonprocessingex.models.entities.Product;

import java.util.List;

public class UserSoldDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductsWithBuyersDto> soldProducts;

    public UserSoldDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsWithBuyersDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsWithBuyersDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
