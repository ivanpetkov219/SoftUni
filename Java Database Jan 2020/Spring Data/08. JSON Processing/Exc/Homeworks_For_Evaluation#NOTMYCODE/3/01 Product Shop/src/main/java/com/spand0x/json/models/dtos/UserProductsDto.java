package com.spand0x.json.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class UserProductsDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private ProductCountDto soldProducts;

    public UserProductsDto() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public ProductCountDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductCountDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
