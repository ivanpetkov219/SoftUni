package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDetailDto {

    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
}
