package com.softuni.jsonprocessingexcars.services.impls;

import com.softuni.jsonprocessingexcars.models.dtos.CarByMakeDto;
import com.softuni.jsonprocessingexcars.models.dtos.CarSeedDto;
import com.softuni.jsonprocessingexcars.models.dtos.CarsAndPartsDto;
import com.softuni.jsonprocessingexcars.models.dtos.PartsViewDto;
import com.softuni.jsonprocessingexcars.models.entities.Car;
import com.softuni.jsonprocessingexcars.models.entities.Part;
import com.softuni.jsonprocessingexcars.repositories.CarRepository;
import com.softuni.jsonprocessingexcars.services.CarService;
import com.softuni.jsonprocessingexcars.services.PartService;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PartService partService;
    private final Random random;
    private final BufferedReader bufferedReader;


    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, PartService partService, Random random, BufferedReader bufferedReader) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.partService = partService;
        this.random = random;
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void seedCars(CarSeedDto[] carSeedDtos) {
        for (CarSeedDto carSeedDto : carSeedDtos) {
            if(this.validationUtil.isValid(carSeedDto)){
                if(this.carRepository
                        .findByMakeAndModelAndTravelledDistance(carSeedDto.getMake(),
                                carSeedDto.getModel(), carSeedDto.getTravelledDistance()) == null){
                    Car car = this.modelMapper.map(carSeedDto, Car.class);
                    car.setParts(this.partService.getRandomParts());
                    System.out.println();
                    this.carRepository.saveAndFlush(car);
                }else {
                    System.out.println("Already in DB");
                }

            }else {
                this.validationUtil
                        .violations(carSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        }
    }

    @Override
    public Car getRandomCar() {
        long randomId = this.random
                .nextInt((int) this.carRepository.count()) + 1;

        return this.carRepository.getOne(randomId);
    }

    @Override
    public List<CarByMakeDto> getAllCarsByMake() throws IOException {
        System.out.println("Enter car make:");
        String make = this.bufferedReader.readLine();
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make).stream().map(c -> this.modelMapper.map(c, CarByMakeDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarsAndPartsDto> getAllCarsAndTheirParts() {
        return this.carRepository.findAll().stream().map(c -> {
            CarsAndPartsDto carsAndPartsDto = this.modelMapper.map(c, CarsAndPartsDto.class);
            List<PartsViewDto> partsViewDtos = new ArrayList<>();
            for (Part part : c.getParts()) {
                partsViewDtos.add(this.modelMapper.map(part, PartsViewDto.class));
            }
            carsAndPartsDto.setParts(partsViewDtos);
            return carsAndPartsDto;
        }).collect(Collectors.toList());
    }
}
