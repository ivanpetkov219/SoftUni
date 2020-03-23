package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.PartSeedDto;
import com.spand0x.cardealer.models.entities.Part;

import java.util.List;

public interface PartService {
    void seedParts(PartSeedDto[] dtos);

    List<Part> getAllParts();
}
