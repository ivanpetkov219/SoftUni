package com.example.xmlprocessingdemo.Service.impl;

import com.example.xmlprocessingdemo.Service.CustomerService;
import com.example.xmlprocessingdemo.dtos.CustomerSeedDto;
import com.example.xmlprocessingdemo.dtos.CustomerViewDto;
import com.example.xmlprocessingdemo.dtos.CustomerViewRootDto;
import com.example.xmlprocessingdemo.entities.Customer;
import com.example.xmlprocessingdemo.repository.CustomerRepository;
import com.example.xmlprocessingdemo.utils.ValidationUtil;
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
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Random random;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Random random) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.random = random;
    }

    @Override
    public void seedCustomer(List<CustomerSeedDto> customers) {
        customers.
                forEach(customerSeedDto -> {
                    if (this.validationUtil.isValid(customerSeedDto)) {
                        if (this.customerRepository.findByNameAndBirthDate(
                                customerSeedDto.getName(), customerSeedDto.getBirthDate()) == null) {

                            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);

                            this.customerRepository.saveAndFlush(customer);
                        } else {
                            System.out.println("Customer is already in DB");
                        }
                    } else {
                        this.validationUtil
                                .getViolations(customerSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = this.random.nextInt((int) this.customerRepository.count()) + 1;

        return this.customerRepository.getOne(randomId);
    }

    @Override
    public void writeAllOrderCustomers() {

    }

    @Override
    public CustomerViewRootDto getAllOrderedCustomers() {
        CustomerViewRootDto customerViewRootDto = new CustomerViewRootDto();

        List<CustomerViewDto> customerViewDtos = this.customerRepository
                .findAllByBirthDateAndIsYoungDriver()
                .stream()
                .map(c ->
                        this.modelMapper.map(c, CustomerViewDto.class))
                .collect(Collectors.toList());
        customerViewRootDto.setCustomers(customerViewDtos);

        return customerViewRootDto;
    }
}
