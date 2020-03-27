package com.example.xmlprocessingdemo.Service;

import com.example.xmlprocessingdemo.dtos.SupplierSeedDto;
import com.example.xmlprocessingdemo.entities.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    void seedSuppliers(List<SupplierSeedDto> supplierSeedDtos);

    Supplier getRandomSupplier();
}
