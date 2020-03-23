package com.softuni.jsonprocessingexcars.services.impls;

import com.softuni.jsonprocessingexcars.models.dtos.PartSeedDto;
import com.softuni.jsonprocessingexcars.models.entities.Part;
import com.softuni.jsonprocessingexcars.repositories.PartRepository;
import com.softuni.jsonprocessingexcars.services.PartService;
import com.softuni.jsonprocessingexcars.services.SupplierService;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final Random random;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, ValidationUtil validationUtil, SupplierService supplierService, Random random) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
        this.random = random;
    }

    @Override
    public void seedParts(PartSeedDto[] partSeedDtos) {
        for (PartSeedDto partSeedDto : partSeedDtos) {
            if (this.validationUtil.isValid(partSeedDto)) {
                if (this.partRepository.findByNameAndPrice(partSeedDto.getName(), partSeedDto.getPrice()) == null) {
                    Part part = this.modelMapper.map(partSeedDto, Part.class);
                    part.setSupplier(this.supplierService.getRandomSupplier());
                    this.partRepository.saveAndFlush(part);
                } else {
                    System.out.println("Part is already in DB.");
                }
            } else {
                this.validationUtil.violations(partSeedDto).stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            }
        }
    }

    @Override
    public Set<Part> getRandomParts() {
        Set<Part> resultSet = new HashSet<>();
        int randomCounter = this.random.nextInt(10) + 10;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = this.random.nextInt((int) this.partRepository.count()) + 1;
            resultSet.add(this.partRepository.getOne(randomId));
        }
        return resultSet;
    }
}
