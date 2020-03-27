package com.softuni.xmlprocessingexercise2.services;

import com.softuni.xmlprocessing.utils.XMLParser;
import com.softuni.xmlprocessingexercise2.entities.Customer;
import com.softuni.xmlprocessingexercise2.entities.Part;
import com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFiveDtos.ExerciseFiveDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.exerciseFiveDtos.ExerciseFiveRootDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.firstExerciseDtos.FirstExerciseCustomerDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.firstExerciseDtos.FirstExerciseRootDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.CustomersImportDto;
import com.softuni.xmlprocessingexercise2.entities.dtos.importDtos.CustomersRootImportDto;
import com.softuni.xmlprocessingexercise2.repositories.CustomerRepository;
import com.softuni.xmlprocessingexercise2.services.interfaces.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private com.softuni.xmlprocessing.utils.XMLParser xmlParser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XMLParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws JAXBException {

        CustomersRootImportDto customersRootImportDto = this.xmlParser.read(CustomersRootImportDto.class, "src/main/resources/files/09. XML-Processing-Exercises/customers.xml");

        for (CustomersImportDto customersImportDto : customersRootImportDto.getCustomersImportDtoList()) {
            Customer customer = this.modelMapper.map(customersImportDto, Customer.class);
            this.customerRepository.save(customer);
        }

    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();

        long rndNumber = random.nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.findById(rndNumber).get();

    }

    @Override
    public long getCount() {
        return this.customerRepository.count();
    }

    @Override
    public void firstExercise() throws JAXBException {
        List<Customer> customerList = this.customerRepository.firstExercise().stream().sorted((p1, p2) -> {
            int comparator = p1.getBirthDate().compareTo(p2.getBirthDate());
            if (comparator == 0) {
                comparator = Boolean.compare(p2.isYoungDriver(), p1.isYoungDriver());
            }
            return comparator;
        }).collect(Collectors.toList());

        List<FirstExerciseCustomerDto> firstExerciseCustomerDtos = customerList
                .stream().map(e -> this.modelMapper.map(e, FirstExerciseCustomerDto.class)).collect(Collectors.toList());
        FirstExerciseRootDto firstExerciseRootDto = new FirstExerciseRootDto(firstExerciseCustomerDtos);

        this.xmlParser.write(firstExerciseRootDto, "src/main/resources/output.xml");

    }

    @Override
    public void exerciseFive() throws JAXBException {
        List<Customer> test = this.customerRepository.exerciseFive();
        System.out.println();
        List<ExerciseFiveDto> customers = this.customerRepository.exerciseFive()
                .stream().map(c -> {
                    int countCars = c.getSales().size();
                    double moneySpent = c.getSales().stream().map(s ->s.getCar().getParts().stream().map(Part::getPrice).mapToDouble(Double::doubleValue).sum()).mapToDouble(Double::doubleValue).sum();
                    return new ExerciseFiveDto(c.getName(), countCars, moneySpent);
                }).sorted((p1, p2) -> {
                    int comparator = Double.compare(p2.getSpentMoney(), p1.getSpentMoney());
                    comparator = comparator == 0 ? Double.compare(p2.getCountCars(), p1.getCountCars()) : comparator;
                    return comparator;
                }).collect(Collectors.toList());

        this.xmlParser.write(new ExerciseFiveRootDto(customers),"src/main/resources/output.xml");


    }
}

//
