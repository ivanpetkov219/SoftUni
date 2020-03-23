package com.spand0x.json.models.dtos;

import com.google.gson.annotations.Expose;
import com.spand0x.json.models.entities.Product;

import java.util.Set;

public class UserSoldDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductBoughtDto> soldProducts;

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

    public Set<ProductBoughtDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductBoughtDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
