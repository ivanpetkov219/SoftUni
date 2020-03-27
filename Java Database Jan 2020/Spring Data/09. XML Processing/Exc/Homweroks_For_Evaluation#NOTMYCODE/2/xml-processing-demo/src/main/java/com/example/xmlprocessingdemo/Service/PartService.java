package com.example.xmlprocessingdemo.Service;

import com.example.xmlprocessingdemo.dtos.PartSeedDto;
import com.example.xmlprocessingdemo.entities.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PartService {
    void seedParts(List<PartSeedDto> partSeedDtos);

    Set<Part> getRandomParts();
}
