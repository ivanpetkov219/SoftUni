package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.CarPartsDto;
import com.spand0x.cardealer.models.dtos.CarPrintDto;
import com.spand0x.cardealer.models.dtos.CarSeedDto;
import com.spand0x.cardealer.models.dtos.PartNamePriceDto;
import com.spand0x.cardealer.models.entities.Car;
import com.spand0x.cardealer.models.entities.Part;
import com.spand0x.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    @Transactional
    public void seedCars(CarSeedDto[] dtos) {
        List<Car> cars = Arrays.stream(dtos)
                .map(dto -> this.modelMapper.map(dto, Car.class))
                .collect(Collectors.toList());
        List<Part> parts = this.partService.getAllParts();
        Random random = new Random();
        cars.forEach(car -> {
            int randNum = random.nextInt(10) + 10;
            for (int i = 0; i < randNum; i++) {
                int randPart = random.nextInt(parts.size());
                car.getParts().add(parts.get(randPart));
            }
        });

        this.carRepository.saveAll(cars);
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarPrintDto> getCarsByMake(String make) {
        List<Car> cars = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc(make);
        List<CarPrintDto> dtos = cars.stream()
                .map(c -> this.modelMapper.map(c, CarPrintDto.class))
                .collect(Collectors.toList());

        return dtos;

    }

    @Override
    @Transactional
    public List<CarPartsDto> getCarsWithParts() {
        List<Car> cars = this.carRepository.findAll();
        List<CarPartsDto> carsDtos = cars.stream()
                .map(c -> {
                    CarPartsDto dto = this.modelMapper.map(c, CarPartsDto.class);
                    List<PartNamePriceDto> partsDto = c.getParts().stream()
                            .map(p -> this.modelMapper.map(p, PartNamePriceDto.class))
                            .collect(Collectors.toList());
                    dto.setParts(partsDto);
                    return dto;
                }).collect(Collectors.toList());
        return carsDtos;
    }

}
