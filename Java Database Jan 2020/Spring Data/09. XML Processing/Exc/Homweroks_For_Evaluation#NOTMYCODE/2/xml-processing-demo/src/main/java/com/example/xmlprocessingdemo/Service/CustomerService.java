package com.example.xmlprocessingdemo.Service;

import com.example.xmlprocessingdemo.dtos.CustomerSeedDto;
import com.example.xmlprocessingdemo.dtos.CustomerViewDto;
import com.example.xmlprocessingdemo.dtos.CustomerViewRootDto;
import com.example.xmlprocessingdemo.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void seedCustomer(List<CustomerSeedDto> customers);

    Customer getRandomCustomer();

    void writeAllOrderCustomers();

    CustomerViewRootDto getAllOrderedCustomers();
}
