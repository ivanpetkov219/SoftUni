package com.softuni.jsonprocessingexcars.repositories;

import com.softuni.jsonprocessingexcars.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
