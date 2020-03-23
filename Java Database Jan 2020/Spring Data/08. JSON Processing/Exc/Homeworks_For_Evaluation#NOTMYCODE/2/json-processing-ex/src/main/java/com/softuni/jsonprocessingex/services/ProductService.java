package com.softuni.jsonprocessingex.services;

import com.softuni.jsonprocessingex.models.dtos.ProductInRangeDto;
import com.softuni.jsonprocessingex.models.dtos.ProductSeedDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> getAllProductsInRange() throws IOException;
}
