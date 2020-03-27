package com.softuni.xmlprocessingexercise2.services;

import com.softuni.xmlprocessing.utils.XMLParser;
import com.softuni.xmlprocessingexercise2.entities.Car;
import com.softuni.xmlprocessingexercise2.entities.Part;
import com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFourDtos.ExerciseFourCarDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFourDtos.ExerciseFourRootDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.CarImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.CarsRootImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.secondExerciseDtos.SecondExerciseDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.secondExerciseDtos.SecondExerciseRootDto;
import com.softuni.xmlprocessingexercise2.repositories.CarRepository;
import com.softuni.xmlprocessingexercise2.services.interfaces.CarService;
import com.softuni.xmlprocessingexercise2.services.interfaces.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final XMLParser xmlParser;
    private final ModelMapper modelMapper;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, XMLParser xmlParser, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public void seedCars() throws JAXBException {
        CarsRootImportDto carsRootImportDto=this.xmlParser.read(CarsRootImportDto.class,"src/main/resources/files/09. XML-Processing-Exercises/cars.xml");

        for (CarImportDto carDto : carsRootImportDto.getCars()) {
            Car car=this.modelMapper.map(carDto,Car.class);
            List<Part> randomParts = this.partService.getRandomParts();
            car.setParts(randomParts);
            this.carRepository.save(car);
        }
        System.out.println();

    }

    @Override
    public Car getRandomCar() {
        Random random=new Random();

        long rndNumber=random.nextInt((int)this.carRepository.count())+1;
        return this.carRepository.findById(rndNumber).get();
    }

    @Override
    public void secondExercise() throws JAXBException {
        List<Car>cars=this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        List<SecondExerciseDto>secondExerciseDto=cars.stream()
                .map(e->this.modelMapper.map(e,SecondExerciseDto.class)).collect(Collectors.toList());

         this.xmlParser.write(new SecondExerciseRootDto(secondExerciseDto),"src/main/resources/output.xml");
    }

    @Override
    public void exerciseFour() throws JAXBException {
        List<ExerciseFourCarDto> cars = this.carRepository.getAllBy()
                .stream()
                .map(e->this.modelMapper.map(e,ExerciseFourCarDto.class)).collect(Collectors.toList());

       this.xmlParser.write(new ExerciseFourRootDto(cars),"src/main/resources/output.xml");
    }
}
