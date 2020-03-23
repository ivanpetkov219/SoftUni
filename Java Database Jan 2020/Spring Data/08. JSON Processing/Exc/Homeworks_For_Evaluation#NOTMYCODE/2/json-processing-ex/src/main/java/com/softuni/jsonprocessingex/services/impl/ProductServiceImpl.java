package com.softuni.jsonprocessingex.services.impl;

import com.softuni.jsonprocessingex.models.dtos.ProductInRangeDto;
import com.softuni.jsonprocessingex.models.dtos.ProductSeedDto;
import com.softuni.jsonprocessingex.models.entities.Product;
import com.softuni.jsonprocessingex.repositories.ProductRepository;
import com.softuni.jsonprocessingex.services.CategoryService;
import com.softuni.jsonprocessingex.services.ProductService;
import com.softuni.jsonprocessingex.services.UserService;
import com.softuni.jsonprocessingex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;
    private final BufferedReader bufferedReader;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService, BufferedReader bufferedReader) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;

        this.categoryService = categoryService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        if (this.productRepository.count() != 0) {
            return;
        }
        Arrays.stream(productSeedDtos).forEach(productSeedDto -> {
            if (this.validationUtil.isValid(productSeedDto)) {
                Product product = this.modelMapper.map(productSeedDto, Product.class);
                product.setSeller(this.userService.getRandomUser());

                Random random = new Random();
                int randomNum = random.nextInt(2);
                if (randomNum == 1) {
                    product.setBuyer(this.userService.getRandomUser());
                }
                product.setCategories(new HashSet<>(this.categoryService.getRandomCategories()));
                this.productRepository.saveAndFlush(product);

            } else {
                this.validationUtil.violations(productSeedDto).stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            }
        });
    }

    @Override
    public List<ProductInRangeDto> getAllProductsInRange() throws IOException {
        System.out.println("Enter lower range bound:");
        long lowerBound = Long.parseLong(bufferedReader.readLine());
        System.out.println("Enter upper range bound:");
        long upperBound = Long.parseLong(bufferedReader.readLine());
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(lowerBound), BigDecimal.valueOf(upperBound))
                .stream().map(p -> {
                    ProductInRangeDto productInRangeDto = this.modelMapper.map(p, ProductInRangeDto.class);
                    productInRangeDto.setSeller(String.format("%s %s", p.getSeller().getFirstName(), p.getSeller().getLastName()));
                    return productInRangeDto;
                }).collect(Collectors.toList());
    }
}
