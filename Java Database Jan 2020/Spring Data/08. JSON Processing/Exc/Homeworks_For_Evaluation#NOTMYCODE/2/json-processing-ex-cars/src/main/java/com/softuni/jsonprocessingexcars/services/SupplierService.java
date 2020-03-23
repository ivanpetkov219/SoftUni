package com.softuni.jsonprocessingexcars.services;

import com.softuni.jsonprocessingexcars.models.dtos.SupplierSeedDto;
import com.softuni.jsonprocessingexcars.models.dtos.LocalSuppliersDto;
import com.softuni.jsonprocessingexcars.models.entities.Supplier;

import java.util.List;


public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

    Supplier getRandomSupplier();

    List<LocalSuppliersDto> localSuppliers();
}
