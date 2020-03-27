package com.example.xmlprocessingdemo.Service;

import com.example.xmlprocessingdemo.dtos.CarSeedDto;
import com.example.xmlprocessingdemo.entities.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    void seedCar(List<CarSeedDto> carSeedDtos);

    Car getRandomCar();
}
