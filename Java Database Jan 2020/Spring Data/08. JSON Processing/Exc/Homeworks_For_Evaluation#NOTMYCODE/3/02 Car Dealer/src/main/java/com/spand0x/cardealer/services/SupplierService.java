package com.spand0x.cardealer.services;


import com.spand0x.cardealer.models.dtos.SupplierLocalDto;
import com.spand0x.cardealer.models.dtos.SupplierSeedDto;
import com.spand0x.cardealer.models.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] dtos);
    Supplier getRandomSupplier();

    List<SupplierLocalDto> getLocalSuppliers();
}
