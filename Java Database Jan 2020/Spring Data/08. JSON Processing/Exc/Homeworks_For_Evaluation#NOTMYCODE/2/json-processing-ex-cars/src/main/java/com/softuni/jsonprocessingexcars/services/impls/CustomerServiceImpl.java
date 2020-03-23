package com.softuni.jsonprocessingexcars.services.impls;

import com.softuni.jsonprocessingexcars.models.dtos.CustomerSeedDto;
import com.softuni.jsonprocessingexcars.models.dtos.CustomerViewDto;
import com.softuni.jsonprocessingexcars.models.entities.Customer;
import com.softuni.jsonprocessingexcars.repositories.CustomerRepository;
import com.softuni.jsonprocessingexcars.services.CustomerService;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

        this.random = random;
    }

    @Override
    public void seedCustomers(CustomerSeedDto[] customerSeedDtos) {
        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            if (this.validationUtil.isValid(customerSeedDto)) {
                if (this.customerRepository
                        .findByNameAndBirthDate(customerSeedDto.getName(),
                                customerSeedDto.getBirthdate()) == null) {

                    Customer customer = this.modelMapper
                            .map(customerSeedDto, Customer.class);

                    this.customerRepository.saveAndFlush(customer);

                } else {
                    System.out.println("Already in DB");
                }
            } else {
                this.validationUtil
                        .violations(customerSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        }
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = this.random.nextInt((int) (this.customerRepository.count() + 1));
        return this.customerRepository.getOne(randomId);
    }

    @Override
    public List<CustomerViewDto> getAllOrderedCustomers() {
     return    this.customerRepository.findAllByBirthDateAndIsYoungDriver().stream().map(c -> this.modelMapper.map(c, CustomerViewDto.class)).collect(Collectors.toList());

    }


}
