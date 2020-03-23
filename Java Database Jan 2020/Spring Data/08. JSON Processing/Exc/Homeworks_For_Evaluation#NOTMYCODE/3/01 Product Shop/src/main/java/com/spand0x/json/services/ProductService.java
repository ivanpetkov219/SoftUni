package com.spand0x.json.services;

import com.spand0x.json.models.dtos.ProductInRangeDto;
import com.spand0x.json.models.dtos.ProductSeedDto;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {
    void seedProducts(ProductSeedDto productSeedDto);

    Set<ProductInRangeDto> getProductsInRange(BigDecimal min, BigDecimal max);

}
