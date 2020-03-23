package com.spand0x.cardealer.controllers;

import com.google.gson.Gson;
import com.spand0x.cardealer.models.dtos.*;
import com.spand0x.cardealer.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.spand0x.cardealer.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;


    public AppController(Gson gson, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.gson = gson;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedSupplier();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        this.saleService.seedSales();
//        orderedCustomersEx();
//        carsFromMakerEx();
//        localSuppliersEx();
//        carsWithPartsEx();
//        totalSalesByCustomerEx();
        salesWithAppliedDiscountEx();
    }

    private void salesWithAppliedDiscountEx() throws IOException {
        List<SaleCustomerCarDiscountDto> sales = this.saleService.getSalesWithDiscount();
        FileWriter fr = new FileWriter(OUTPUT_QUERY_SIX);
        this.gson.toJson(sales,fr);
        fr.flush();
    }

    private void totalSalesByCustomerEx() throws IOException {
        List<CustomerSalesDto> customers = this.customerService.getCustomersWithCars();
        FileWriter fr = new FileWriter(OUTPUT_QUERY_FIVE);
        this.gson.toJson(customers,fr);
        fr.flush();
    }

    private void localSuppliersEx() throws IOException {
        List<SupplierLocalDto> suppliers = this.supplierService.getLocalSuppliers();
        FileWriter fr = new FileWriter(OUTPUT_QUERY_THREE);
        this.gson.toJson(suppliers,fr);
        fr.flush();
    }

    private void carsWithPartsEx() throws IOException {
        List<CarPartsDto> dtos = this.carService.getCarsWithParts();
        FileWriter fr = new FileWriter(OUTPUT_QUERY_FOUR);
        this.gson.toJson(dtos,fr);
        fr.flush();
    }

    private void carsFromMakerEx() throws IOException {
        String maker = "Toyota";
        List<CarPrintDto> cars = this.carService.getCarsByMake(maker);
        FileWriter fr = new FileWriter(OUTPUT_QUERY_TWO);
        this.gson.toJson(cars,fr);
        fr.flush();
    }

    private void orderedCustomersEx() throws IOException {
        List<CustomerPrintDto> customers = this.customerService.getOrderedCustomers();
        FileWriter fr = new FileWriter(OUTPUT_QUERY_ONE);
        this.gson.toJson(customers,fr);
        fr.flush();
    }

    private void seedCustomers() throws FileNotFoundException {
        CustomerSeedDto[] customerSeedDtos =
                this.gson.fromJson(new FileReader(CUSTOMER_FILE_PATH),CustomerSeedDto[].class);
        this.customerService.seedCustomers(customerSeedDtos);
    }

    private void seedCars() throws FileNotFoundException {
        CarSeedDto[] carSeedDtos = this.gson.fromJson(new FileReader(CAR_FILE_PATH), CarSeedDto[].class);
        this.carService.seedCars(carSeedDtos);
    }

    private void seedParts() throws FileNotFoundException {
        PartSeedDto[] partSeedDtos = this.gson.fromJson(new FileReader(PART_FILE_PATH), PartSeedDto[].class);
        this.partService.seedParts(partSeedDtos);
    }

    private void seedSupplier() throws FileNotFoundException {
        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(new FileReader(SUPPLIER_FILE_PATH), SupplierSeedDto[].class);
        this.supplierService.seedSuppliers(supplierSeedDtos);
    }
}
