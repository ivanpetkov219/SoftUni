package com.softuni.xmlprocessingexercise2.services;

import com.softuni.xmlprocessing.utils.XMLParser;
import com.softuni.xmlprocessingexercise2.entities.Supplier;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.SupplierImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.SupplierRootImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.thirdExerciseDtos.ThirdExerciseDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.thirdExerciseDtos.ThirdExerciseRootDto;
import com.softuni.xmlprocessingexercise2.repositories.SupplierRepository;
import com.softuni.xmlprocessingexercise2.services.interfaces.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final XMLParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, XMLParser xmlParser, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSuppliers() throws JAXBException {

        SupplierRootImportDto supplierRootImportDto=this.xmlParser.read(SupplierRootImportDto.class,"src/main/resources/files/09. XML-Processing-Exercises/suppliers.xml");

        for (SupplierImportDto currentSupplier : supplierRootImportDto.getSuppliersImport()) {

            Supplier supplier=this.modelMapper.map(currentSupplier,Supplier.class);
            this.supplierRepository.save(supplier);
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random=new Random();

        long rndNumber=random.nextInt((int)this.supplierRepository.count())+1;
        return this.supplierRepository.findById(rndNumber).get();
    }

    @Override
    public void thirdExercise() throws JAXBException {
        List<ThirdExerciseDto> res= this.supplierRepository.thirdExercise()
                .stream().map(e->new ThirdExerciseDto((long)e[0],String.valueOf(e[1]),(int)e[2])).collect(Collectors.toList());


        this.xmlParser.write(new ThirdExerciseRootDto(res),"src/main/resources/output.xml");
    }
}
