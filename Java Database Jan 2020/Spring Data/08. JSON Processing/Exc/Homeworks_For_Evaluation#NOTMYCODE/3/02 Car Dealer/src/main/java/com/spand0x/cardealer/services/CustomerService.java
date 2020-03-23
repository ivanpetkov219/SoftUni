package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.CustomerSalesDto;
import com.spand0x.cardealer.models.dtos.CustomerSeedDto;
import com.spand0x.cardealer.models.dtos.CustomerPrintDto;
import com.spand0x.cardealer.models.entities.Customer;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomerSeedDto[] dtos);

    List<Customer> getAllCustomers();

    List<CustomerPrintDto> getOrderedCustomers();

    List<CustomerSalesDto> getCustomersWithCars();
}
