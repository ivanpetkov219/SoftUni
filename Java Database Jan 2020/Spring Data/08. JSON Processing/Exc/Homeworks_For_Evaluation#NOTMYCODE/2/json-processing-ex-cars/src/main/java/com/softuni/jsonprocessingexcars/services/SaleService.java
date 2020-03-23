package com.softuni.jsonprocessingexcars.services;

import com.softuni.jsonprocessingexcars.models.dtos.SaleViewDto;

import java.util.List;

public interface SaleService {
    void seedSales();
    List<SaleViewDto> salesWithDiscount();
}
