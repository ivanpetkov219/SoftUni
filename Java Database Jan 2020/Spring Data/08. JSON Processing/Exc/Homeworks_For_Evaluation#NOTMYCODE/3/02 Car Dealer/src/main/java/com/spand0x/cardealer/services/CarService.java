package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.CarPartsDto;
import com.spand0x.cardealer.models.dtos.CarPrintDto;
import com.spand0x.cardealer.models.dtos.CarSeedDto;
import com.spand0x.cardealer.models.entities.Car;

import java.util.List;

public interface CarService {
    void seedCars(CarSeedDto[] dtos);

    List<Car> getAllCars();

    List<CarPrintDto> getCarsByMake(String make);

    List<CarPartsDto> getCarsWithParts();
}
