package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameEditDto {

    @Pattern(regexp = "[A-Z][A-Za-z]{3,100}", message = "Title is not valid")
    private String title;

    @DecimalMin(value = "0", message = "Price cannot be negative")
    private BigDecimal price;

    @Min(value = 0, message = "Size cannot be negative")
    private double size;

    @Size(min = 11, max = 11, message = "Trailer is not valid")
    private String trailer;

    @Pattern(regexp = "^http:\\/\\/.+|https:\\/\\/.+", message = "Image is not valid")
    private String image;

    @Size(min = 20, message = "Description is not valid")
    private String description;

    private LocalDate releaseDate;
}
