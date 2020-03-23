package com.spand0x.json.controllers;

import com.google.gson.Gson;
import com.spand0x.json.constants.GlobalConstants;
import com.spand0x.json.models.dtos.*;
import com.spand0x.json.services.CategoryService;
import com.spand0x.json.services.ProductService;
import com.spand0x.json.services.UserService;
import com.spand0x.json.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.spand0x.json.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, UserService userService, ProductService productService, CategoryService categoryService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedUsers();
//        this.seedCategories();
//        this.seedProducts();
//        productsInRangeEx();
//        successfullySoldProductsEx();
//        categoriesByProductsCountEx();
        usersAndProducts();

    }

    private void usersAndProducts() throws IOException {
        UserCountDto usersAndProducts = this.userService.getUsersAndProducts();
        String content = this.gson.toJson(usersAndProducts);
        this.fileIOUtil.write(content,OUTPUT_FOURTH_QUERY);
    }

    private void categoriesByProductsCountEx() throws IOException {
        List<CategoryStatisticDto> categories = this.categoryService.getCategoriesStatistics();
        String content = gson.toJson(categories);
        this.fileIOUtil.write(content,OUTPUT_THIRD_QUERY);
    }

    private void successfullySoldProductsEx() throws IOException {
        List<UserSoldDto> users = this.userService.getUsersWithSoldProducts();
        String content = this.gson.toJson(users);
        this.fileIOUtil.write(content,OUTPUT_SECOND_QUERY);
    }

    private void productsInRangeEx() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ex 1 Products In Range without a buyer:");
        System.out.print("Please enter min price range: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Please enter max price range: ");
        double max = Double.parseDouble(scanner.nextLine());
        System.out.println(min + " " + max);
        Set<ProductInRangeDto> products = this.productService.getProductsInRange(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
        String content = this.gson.toJson(products);
        this.fileIOUtil.write(content, OUTPUT_FIRST_QUERY);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos =
                this.gson.fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);
        Arrays.stream(dtos).forEach(this.userService::seedUser);
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos =
                this.gson.fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);
        Arrays.stream(dtos).forEach(this.productService::seedProducts);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos =
                this.gson.fromJson(new FileReader(CATEGORIES_FILE_PATH), CategorySeedDto[].class);
        Arrays.stream(dtos).forEach(this.categoryService::seedCategory);
    }


}
