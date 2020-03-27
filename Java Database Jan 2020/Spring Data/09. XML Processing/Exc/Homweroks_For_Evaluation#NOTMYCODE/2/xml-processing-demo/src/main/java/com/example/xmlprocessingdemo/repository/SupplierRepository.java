package com.example.xmlprocessingdemo.repository;

import com.example.xmlprocessingdemo.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    Supplier findByName(String name);
}
