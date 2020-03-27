package com.xmlproductshop.repositories;

import com.xmlproductshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByNameAndPrice(String name, BigDecimal price);

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal price,BigDecimal high);
}
