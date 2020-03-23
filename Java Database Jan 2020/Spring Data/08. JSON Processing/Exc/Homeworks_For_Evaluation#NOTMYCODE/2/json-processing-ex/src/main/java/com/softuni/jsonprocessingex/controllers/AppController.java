package com.softuni.jsonprocessingex.controllers;

import com.google.gson.Gson;
import com.softuni.jsonprocessingex.models.dtos.*;
import com.softuni.jsonprocessingex.services.CategoryService;
import com.softuni.jsonprocessingex.services.ProductService;
import com.softuni.jsonprocessingex.services.UserService;
import com.softuni.jsonprocessingex.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.softuni.jsonprocessingex.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed the Data
//        this.seedCategories();
//        this.seedUsers();
//        this.seedProducts();

        // Query 1 - Products in Range
//        this.writeProductInRange();

        // Query 2 - Successfully Sold Products
//        this.writeSuccessfullySoldProducts();

        // Query 3 - Categories by Products Count
//        this.writeCategoriesByProductsCount();

        // Query 4 - Users and Products
          this.usersAndProducts();

    }

    private void writeCategoriesByProductsCount() throws IOException {
        List<CategoryByProductsCountDto> dtos = this.categoryService.getAllCategoriesByProductsCount();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX3_OUTPUT_FILE_PATH);
    }

    private void writeSuccessfullySoldProducts() throws IOException {
        List<UserSoldDto> dtos = this.userService.getSellers();
        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX2_OUTPUT_FILE_PATH);
    }

    private void writeProductInRange() throws IOException {
        List<ProductInRangeDto> dtos = this.productService.getAllProductsInRange();

        String json = this.gson.toJson(dtos);
        this.fileIOUtil.write(json, EX1_OUTPUT_FILE_PATH);
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson.fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);
        this.productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos = this.gson.fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);
        this.userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = this.gson.fromJson(new FileReader(CATEGORIES_FILE_PATH), CategorySeedDto[].class);
        System.out.println();
        this.categoryService.seedCategories(dtos);
    }

    private void usersAndProducts() throws IOException {
        UsersViewDto viewDto = this.userService.getAllSellersByCount();
        String json = this.gson.toJson(viewDto);
        this.fileIOUtil.write(json, EX4_OUTPUT_FILE_PATH);

    }
}
