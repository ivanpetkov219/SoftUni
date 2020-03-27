package com.softuni.xmlprocessingexercise2.repositories;

import com.softuni.xmlprocessingexercise2.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository  extends JpaRepository<Sale,Long> {


    List<Sale> findAllBy();
}
