package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.SaleCustomerCarDiscountDto;
import com.spand0x.cardealer.models.entities.Car;
import com.spand0x.cardealer.models.entities.Customer;
import com.spand0x.cardealer.models.entities.Part;
import com.spand0x.cardealer.models.entities.Sale;
import com.spand0x.cardealer.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void seedSales() {
        Random random = new Random();
        List<Car> cars = this.carService.getAllCars();
        List<Customer> customers = this.customerService.getAllCustomers();
        int randNum = random.nextInt(cars.size() / 4);
        double[] discounts = {0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        List<Sale> sales = new ArrayList<>();
        for (int i = 0; i < randNum; i++) {
            Sale sale = new Sale();
            int randCar = random.nextInt(cars.size());
            int randCustomer = random.nextInt(customers.size());
            int randDiscount = random.nextInt(discounts.length);
            sale.setCar(cars.get(randCar));
            Customer customer = customers.get(randCustomer);
            sale.setCustomer(customer);
            sale.setDiscount(discounts[randDiscount]);
            if (customer.isYoungDriver()) {
                sale.setDiscount(sale.getDiscount() + 0.05);
            }
            sales.add(sale);
        }
        for (Sale sale : sales) {
            this.saleRepository.saveAndFlush(sale);
        }
    }

    @Override
    @Transactional
    public List<SaleCustomerCarDiscountDto> getSalesWithDiscount() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleCustomerCarDiscountDto> salesDto = new ArrayList<>();
        for (Sale sale : sales) {
            SaleCustomerCarDiscountDto dto = this.modelMapper.map(sale,SaleCustomerCarDiscountDto.class);
            BigDecimal price = new BigDecimal(0);
            for (Part part : sale.getCar().getParts()) {
                price = price.add(part.getPrice());
            }
            dto.setPrice(price);
            BigDecimal priceDiscount = BigDecimal.valueOf(price.doubleValue() - (price.doubleValue() * dto.getDiscount()));
            dto.setPriceWithDiscount(priceDiscount);
            salesDto.add(dto);
        }
        return salesDto;
    }
}
