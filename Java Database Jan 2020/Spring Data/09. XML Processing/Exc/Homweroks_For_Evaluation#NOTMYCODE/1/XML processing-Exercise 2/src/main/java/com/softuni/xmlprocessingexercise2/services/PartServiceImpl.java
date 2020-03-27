package com.softuni.xmlprocessingexercise2.services;

import com.softuni.xmlprocessing.utils.XMLParser;
import com.softuni.xmlprocessingexercise2.entities.Part;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.PartImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.PartsRootImportDto;
import com.softuni.xmlprocessingexercise2.repositories.PartRepository;
import com.softuni.xmlprocessingexercise2.services.interfaces.PartService;
import com.softuni.xmlprocessingexercise2.services.interfaces.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper mapper ;
    private final XMLParser xmlParser;
    private final SupplierService supplierService;


    public PartServiceImpl(PartRepository partRepository, ModelMapper mapper, XMLParser xmlParser, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts() throws JAXBException {
        PartsRootImportDto partsRootImportDto=this.xmlParser.read(PartsRootImportDto.class,"src/main/resources/files/09. XML-Processing-Exercises/parts.xml");
        for (PartImportDto partDto : partsRootImportDto.getParts()) {
            Part part =this.mapper.map(partDto,Part.class);
            part.setSupplier(this.supplierService.getRandomSupplier());
            this.partRepository.save(part);

        }

    }

    @Override
    public List<Part> getRandomParts() {
        Random random=new Random();
        List<Part>randomParts = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            long rndNumber=random.nextInt((int) this.partRepository.count())+1;

            randomParts.add(this.partRepository.findById(rndNumber).get());
        }
        

        return randomParts;
    }
}
