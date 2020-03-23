package com.spand0x.json.models.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {

    @Expose
    private String name;

    public CategorySeedDto(String name) {
        this.name = name;
    }


    @Length(min = 3, max = 15,message = "Category name must be between 3 and 15 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
