package com.softuni.xmlprocessingexercise2;

import com.softuni.xmlprocessingexercise2.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {
    private final  SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public init(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seeding the data
//        this.supplierService.seedSuppliers();
//        this.partService.seedParts();
//        this.carService.seedCars();
//        this.customerService.seedCustomers();
//        this.saleService.seedSales();


        //Почти всяка задача вади различен резултат,спрямо този в документа и това е нормално заради Random-a
//            this.customerService.firstExercise();
//            this.carService.secondExercise();
//          this.supplierService.thirdExercise();
//        this.carService.exerciseFour();
//        this.customerService.exerciseFive();
//            this.saleService.exerciseSix();
    }



}
