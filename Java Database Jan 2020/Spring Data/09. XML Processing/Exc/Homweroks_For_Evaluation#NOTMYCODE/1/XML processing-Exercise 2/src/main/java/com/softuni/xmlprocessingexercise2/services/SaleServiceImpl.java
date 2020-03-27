package com.softuni.xmlprocessingexercise2.services;

import com.softuni.xmlprocessing.utils.XMLParser;
import com.softuni.xmlprocessingexercise2.entities.Car;
import com.softuni.xmlprocessingexercise2.entities.Customer;
import com.softuni.xmlprocessingexercise2.entities.Sale;
import com.softuni.xmlprocessingexercise2.repositories.SaleRepository;
import com.softuni.xmlprocessingexercise2.services.interfaces.CarService;
import com.softuni.xmlprocessingexercise2.services.interfaces.CustomerService;
import com.softuni.xmlprocessingexercise2.services.interfaces.SaleService;
import com.softuni.xmlprocessingexercise2.utils.enums.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final com.softuni.xmlprocessing.utils.XMLParser xmlParser;
    private final CarService carService;
    private final CustomerService customerService;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, XMLParser xmlParser, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.customerService = customerService;

    }

    @Override
    public void seedSales() {
        Random random = new Random();

        for (int i = 0; i <this.customerService.getCount() ; i++) {
            Customer randomCustomer = this.customerService.getRandomCustomer();
            Car randomCar = this.carService.getRandomCar();

            int randomInt = random.nextInt(Discount.values().length);
            double discount = Discount.values()[randomInt].getValue();

            discount=randomCustomer.isYoungDriver()?discount+5: discount;
            Sale sale=new Sale(discount,randomCar,randomCustomer);
            this.saleRepository.save(sale);

        }

    }

    @Override
    public void exerciseSix() {
        System.out.println("No exercise...");
    }
}
