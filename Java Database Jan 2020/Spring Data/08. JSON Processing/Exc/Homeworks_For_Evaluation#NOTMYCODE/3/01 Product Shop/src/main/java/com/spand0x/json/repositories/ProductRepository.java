package com.spand0x.json.repositories;

import com.spand0x.json.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal min,BigDecimal max);
}
