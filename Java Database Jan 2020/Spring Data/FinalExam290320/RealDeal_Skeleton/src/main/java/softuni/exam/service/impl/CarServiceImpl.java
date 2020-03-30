package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import javax.swing.text.DateFormatter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.CARS_FILE_PATH;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder result = new StringBuilder();

        CarSeedDto[] dtos = this.gson
                .fromJson(new FileReader(CARS_FILE_PATH), CarSeedDto[].class);

        Arrays.stream(dtos).forEach(carSeedDto -> {
            if (this.validationUtil.isValid(carSeedDto)) {
                if(this.carRepository.findByMakeAndModelAndKilometers(carSeedDto.getMake(),
                        carSeedDto.getModel(), carSeedDto.getKilometers()) == null){

                    Car car = this.modelMapper.map(carSeedDto, Car.class);

                    if(carSeedDto.getRegisteredOn() != null){
                        LocalDate registeredOn = LocalDate.parse(carSeedDto.getRegisteredOn(), DateTimeFormatter.ofPattern("d/M/yyyy"));
                        car.setRegisteredOn(registeredOn);
                    }

                    this.carRepository.saveAndFlush(car);

                    result.append(String.format("Successfully imported car - %s - %s", car.getMake(), car.getModel()));


                }else {
                    result.append("Car already exists in database!");
                }

            } else {
                result.append("Invalid car");
            }
            result.append(System.lineSeparator());
        });


        return result.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return "";
    }

    @Override
    public Car getCarById(long id) {
        return this.carRepository.findById(id);
    }
}
