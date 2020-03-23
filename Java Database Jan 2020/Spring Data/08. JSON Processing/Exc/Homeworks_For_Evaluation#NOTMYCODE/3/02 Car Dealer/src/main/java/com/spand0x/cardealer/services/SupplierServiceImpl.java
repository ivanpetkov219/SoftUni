package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.SupplierLocalDto;
import com.spand0x.cardealer.models.dtos.SupplierSeedDto;
import com.spand0x.cardealer.models.entities.Supplier;
import com.spand0x.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;


    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSuppliers(SupplierSeedDto[] dtos) {
        for (SupplierSeedDto dto :
                dtos) {
            Supplier supplier = this.modelMapper.map(dto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random = new Random();
        int randNum = random.nextInt((int) this.supplierRepository.count())+1;
        return this.supplierRepository.getOne((long) randNum);

    }

    @Override
    @Transactional
    public List<SupplierLocalDto> getLocalSuppliers() {
        Set<Supplier> suppliers = this.supplierRepository.getAllByImporterFalse();
         return suppliers.stream()
                .map(s -> {
                    SupplierLocalDto dto = this.modelMapper.map(s, SupplierLocalDto.class);
                    dto.setPartsCount(s.getParts().size());
                    return dto;
                }).collect(Collectors.toList());
    }
}
