package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameViewDto {
    private String title;
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format("%s %.2f", this.title, this.price);
    }
}
