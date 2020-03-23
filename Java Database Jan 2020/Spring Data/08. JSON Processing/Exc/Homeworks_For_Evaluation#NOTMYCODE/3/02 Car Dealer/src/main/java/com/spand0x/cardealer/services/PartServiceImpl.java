package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.PartSeedDto;
import com.spand0x.cardealer.models.entities.Part;
import com.spand0x.cardealer.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts(PartSeedDto[] dtos) {
        for (PartSeedDto dto : dtos) {
            Part part = this.modelMapper.map(dto,Part.class);
            part.setSupplier(this.supplierService.getRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }
    }

    @Override
    public List<Part> getAllParts() {
        return this.partRepository.findAll();
    }
}
