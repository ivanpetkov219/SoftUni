package com.example.spingautomappingex.domain.dtos;

import com.example.spingautomappingex.domain.entities.Order;

import java.math.BigDecimal;
import java.util.Set;

public class AllGamesDto {
   private String title;
   private BigDecimal price;

    public AllGamesDto() {
    }

    public AllGamesDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
