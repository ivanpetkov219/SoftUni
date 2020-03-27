package com.example.xmlprocessingdemo.Service.impl;

import com.example.xmlprocessingdemo.Service.CarService;
import com.example.xmlprocessingdemo.Service.CustomerService;
import com.example.xmlprocessingdemo.Service.SaleService;
import com.example.xmlprocessingdemo.entities.Sale;
import com.example.xmlprocessingdemo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final Random random;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, Random random) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.random = random;
    }


    @Override
    public void seedSales() {
        for (int i = 0; i < 20; i++) {
            Sale sale = new Sale();

            sale.setDiscount(this.setRandomDiscount());
            sale.setCar(this.carService.getRandomCar());
            sale.setCustomers(this.customerService.getRandomCustomer());

            this.saleRepository.saveAndFlush(sale);
        }
    }

    private Double setRandomDiscount() {
        Double[] discounts =
                new Double[]{0D, 0.05, 0.10, 0.15, 0.20, 0.30, 0.40, 0.50};

        return discounts[this.random.nextInt(discounts.length)];
    }
}
