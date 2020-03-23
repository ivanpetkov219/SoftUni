package com.softuni.jsonprocessingexcars.services;

import com.softuni.jsonprocessingexcars.models.dtos.CarByMakeDto;
import com.softuni.jsonprocessingexcars.models.dtos.CarSeedDto;
import com.softuni.jsonprocessingexcars.models.dtos.CarsAndPartsDto;
import com.softuni.jsonprocessingexcars.models.entities.Car;

import java.io.IOException;
import java.util.List;


public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);

    Car getRandomCar();

    List<CarByMakeDto> getAllCarsByMake() throws IOException;

    List<CarsAndPartsDto> getAllCarsAndTheirParts();
}
