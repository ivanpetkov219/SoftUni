package com.softuni.jsonprocessingexcars.services;

import com.softuni.jsonprocessingexcars.models.dtos.CustomerSeedDto;
import com.softuni.jsonprocessingexcars.models.dtos.CustomerViewDto;
import com.softuni.jsonprocessingexcars.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    void seedCustomers(CustomerSeedDto[] customerSeedDtos);
    Customer getRandomCustomer();

    List<CustomerViewDto> getAllOrderedCustomers();
}
