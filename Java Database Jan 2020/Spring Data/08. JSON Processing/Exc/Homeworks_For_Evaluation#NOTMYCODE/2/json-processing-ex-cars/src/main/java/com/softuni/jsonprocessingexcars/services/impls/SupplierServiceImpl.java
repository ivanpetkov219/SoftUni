package com.softuni.jsonprocessingexcars.services.impls;

import com.softuni.jsonprocessingexcars.models.dtos.LocalSuppliersDto;
import com.softuni.jsonprocessingexcars.models.dtos.SupplierSeedDto;
import com.softuni.jsonprocessingexcars.models.entities.Supplier;
import com.softuni.jsonprocessingexcars.repositories.SupplierRepository;
import com.softuni.jsonprocessingexcars.services.SupplierService;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.random = random;
    }

    @Override
    public void seedSuppliers(SupplierSeedDto[] supplierSeedDtos) {
        if(this.supplierRepository.count() > 0) {

        }else {
            for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
                if (this.validationUtil.isValid(supplierSeedDto)) {
                    if (this.supplierRepository.findByName(supplierSeedDto.getName()) == null) {
                        Supplier supplier = this.modelMapper.map(supplierSeedDto, Supplier.class);
                        this.supplierRepository.saveAndFlush(supplier);
                    } else {
                        System.out.println("Supplier with that name already exists.");
                    }
                } else {
                    this.validationUtil.violations(supplierSeedDto).stream()
                            .map(ConstraintViolation::getMessage).forEach(System.out::println);
                }
            }
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        long randomId = this.random.nextInt((int) this.supplierRepository.count()) +1;

        return this.supplierRepository.getOne(randomId);
    }

    @Override
    public List<LocalSuppliersDto> localSuppliers() {
        return this.supplierRepository.findAllByImporterIsFalse().stream().map(s -> {
            LocalSuppliersDto localSuppliersDto = this.modelMapper.map(s, LocalSuppliersDto.class);
            localSuppliersDto.setPartsCount(s.getParts().size());
            return localSuppliersDto;
        }).collect(Collectors.toList());
    }
}
