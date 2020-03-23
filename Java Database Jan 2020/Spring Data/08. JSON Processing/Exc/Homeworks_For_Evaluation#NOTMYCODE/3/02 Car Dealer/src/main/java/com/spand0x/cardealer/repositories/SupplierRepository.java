package com.spand0x.cardealer.repositories;

import com.spand0x.cardealer.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Set<Supplier> getAllByImporterFalse();
}
