package com.softuni.jsonprocessingexcars.services;

import com.softuni.jsonprocessingexcars.models.dtos.PartSeedDto;
import com.softuni.jsonprocessingexcars.models.entities.Part;

import java.util.Set;

public interface PartService {
    void seedParts(PartSeedDto[] partSeedDtos);

    Set<Part> getRandomParts();
}
