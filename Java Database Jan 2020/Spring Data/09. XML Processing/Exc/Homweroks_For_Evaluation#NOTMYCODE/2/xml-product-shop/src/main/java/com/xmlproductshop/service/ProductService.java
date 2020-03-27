package com.xmlproductshop.service;

import com.xmlproductshop.dtos.CategorySeedDto;
import com.xmlproductshop.dtos.ProductSeedDto;
import com.xmlproductshop.dtos.ProductViewRootDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductService {
    void seedProduct(List<ProductSeedDto>productSeedDtos);
    List<ProductViewRootDto> getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high);
}
