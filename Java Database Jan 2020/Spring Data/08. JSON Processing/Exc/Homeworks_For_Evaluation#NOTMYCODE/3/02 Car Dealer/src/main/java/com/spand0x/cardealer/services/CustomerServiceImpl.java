package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.CustomerPrintDto;
import com.spand0x.cardealer.models.dtos.CustomerSalesDto;
import com.spand0x.cardealer.models.dtos.CustomerSeedDto;
import com.spand0x.cardealer.models.entities.Customer;
import com.spand0x.cardealer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers(CustomerSeedDto[] dtos) {
        for (CustomerSeedDto dto : dtos) {
            Customer customer = this.modelMapper.map(dto, Customer.class);
            LocalDateTime birthDay = LocalDateTime.parse(dto.getBirthDate());
            customer.setBirthDate(birthDay);
            System.out.println("asd");
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    @Transactional
    public List<CustomerPrintDto> getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.getAllByIdIsNotNullOrderByBirthDateAscYoungDriverAsc();

        List<CustomerPrintDto> customersDto = customers.stream()
                .map(c -> {
                    CustomerPrintDto dto = this.modelMapper.map(c, CustomerPrintDto.class);
                    dto.setBirthDay(c.getBirthDate().format(DateTimeFormatter.ISO_DATE_TIME));
                    return dto;
                })
                .collect(Collectors.toList());
        return customersDto;
    }

    @Override
    public List<CustomerSalesDto> getCustomersWithCars() {
        List<List<String>> customersWithSales = this.customerRepository.getCustomersWithSales();
        System.out.println("Asdasd");
        List<CustomerSalesDto> dtos = new LinkedList<>();
        for (List<String> customer : customersWithSales) {
            CustomerSalesDto csdto = new CustomerSalesDto();
            csdto.setFullName(customer.get(0));
            csdto.setBoughtCars(Integer.parseInt(customer.get(1)));
            csdto.setSpentMoney(new BigDecimal(customer.get(2)));
            dtos.add(csdto);
        }
        return dtos;
    }
}
