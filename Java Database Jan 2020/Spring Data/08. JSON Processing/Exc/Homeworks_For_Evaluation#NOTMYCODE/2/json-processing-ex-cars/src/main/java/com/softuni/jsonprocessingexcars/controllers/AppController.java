package com.softuni.jsonprocessingexcars.controllers;


import com.google.gson.Gson;
import com.softuni.jsonprocessingexcars.models.dtos.*;
import com.softuni.jsonprocessingexcars.services.*;

import com.softuni.jsonprocessingexcars.utils.FileIOUtil;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.softuni.jsonprocessingexcars.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {


    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final ValidationUtil validationUtil;
    private final CarService carService;
    private final Gson gson;
    private final FileIOUtil fileIOUtil;


    @Autowired
    public AppController(SupplierService supplierService, PartService partService, CustomerService customerService, SaleService saleService, ValidationUtil validationUtil, CarService carService, Gson gson, FileIOUtil fileIOUtil) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.validationUtil = validationUtil;
        this.carService = carService;
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedSuppliersEx();
//        this.seedPartsEx();
//        this.seedCustomersEx();
//        this.seedCarsEx();
//        this.seedSalesEx();
        // Q1
//        this.writeOrderedCustomers();

        //Q2
//        this.writeCarsFromMake();

        //Q3
//        this.writeLocalSuppliers();
        //Q4
//        this.writeCarsAndParts();
        //Q6
//        this.writeSalesDiscounts();
    }

    private void writeSalesDiscounts() throws IOException {
        List<SaleViewDto> dtos = this.saleService.salesWithDiscount();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX6_FILE_PATH);
    }

    private void writeCarsAndParts() throws IOException {
        List<CarsAndPartsDto> dtos = this.carService.getAllCarsAndTheirParts();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX4_FILE_PATH);
    }

    private void writeLocalSuppliers() throws IOException {
        List<LocalSuppliersDto> dtos = this.supplierService.localSuppliers();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX3_FILE_PATH);
    }

    private void writeCarsFromMake() throws IOException {
        List<CarByMakeDto> dtos = this.carService.getAllCarsByMake();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX2_FILE_PATH);
    }

    private void writeOrderedCustomers() throws IOException {
        List<CustomerViewDto> dtos =this.customerService.getAllOrderedCustomers();
        String json =this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX1_FILE_PATH);
    }

    private void seedCarsEx() throws FileNotFoundException {
        CarSeedDto[] dtos = this.gson.fromJson(new FileReader(CARS_FILE_PATH), CarSeedDto[].class);
        this.carService.seedCars(dtos);
    }

    private void seedSalesEx() {
        this.saleService.seedSales();
    }

    private void seedCustomersEx() throws JAXBException, FileNotFoundException {
        CustomerSeedDto[] dtos = this.gson.fromJson(new FileReader(CUSTOMERS_FILE_PATH), CustomerSeedDto[].class);
        this.customerService.seedCustomers(dtos);
    }


    private void seedPartsEx() throws JAXBException, FileNotFoundException {
        PartSeedDto[] dtos = this.gson.fromJson(new FileReader(PARTS_FILE_PATH), PartSeedDto[].class);
        this.partService.seedParts(dtos);
    }

    private void seedSuppliersEx() throws JAXBException, FileNotFoundException {
        SupplierSeedDto[] dtos = this.gson.fromJson(new FileReader(SUPPLIERS_FILE_PATH), SupplierSeedDto[].class);
        this.supplierService.seedSuppliers(dtos);

    }
}
