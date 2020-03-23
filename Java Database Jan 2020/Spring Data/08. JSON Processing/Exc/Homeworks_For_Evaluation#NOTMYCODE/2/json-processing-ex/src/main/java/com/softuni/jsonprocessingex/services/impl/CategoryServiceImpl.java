package com.softuni.jsonprocessingex.services.impl;

import com.softuni.jsonprocessingex.models.dtos.CategoryByProductsCountDto;
import com.softuni.jsonprocessingex.models.dtos.CategorySeedDto;
import com.softuni.jsonprocessingex.models.entities.Category;
import com.softuni.jsonprocessingex.models.entities.Product;
import com.softuni.jsonprocessingex.repositories.CategoryRepository;
import com.softuni.jsonprocessingex.services.CategoryService;
import com.softuni.jsonprocessingex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(categorySeedDtos).forEach(categorySeedDto -> {
            if (this.validationUtil.isValid(categorySeedDto)) {
                Category category = this.modelMapper.map(categorySeedDto, Category.class);
                this.categoryRepository.saveAndFlush(category);
            } else {
                this.validationUtil.violations(categorySeedDto).stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            }
        });
    }

    @Override
    public List<Category> getRandomCategories() {
        Random random = new Random();
        List<Category> resultList = new ArrayList<>();
        int randomCounter = random.nextInt(3) + 1;
        for (int i = 0; i < randomCounter; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count()) + 1;
            resultList.add(this.categoryRepository.getOne(randomId));
        }
        return resultList;
    }

    @Override
    public List<CategoryByProductsCountDto> getAllCategoriesByProductsCount() {
        return this.categoryRepository.categoriesByProductsCount().stream().map(c -> {
            CategoryByProductsCountDto categoryByProductsCountDto = this.modelMapper.map(c, CategoryByProductsCountDto.class);
            BigDecimal totalPrice = new BigDecimal(0);

            for (Product product : c.getProducts()) {
               totalPrice = totalPrice.add(product.getPrice());
            }
            categoryByProductsCountDto.setAveragePrice(totalPrice.divide(BigDecimal.valueOf(c.getProducts().size()), 6));
            categoryByProductsCountDto.setTotalRevenue(totalPrice);
            categoryByProductsCountDto.setProductsCount(c.getProducts().size());
            categoryByProductsCountDto.setCategory(c.getName());
            return categoryByProductsCountDto;
        }).collect(Collectors.toList());
    }
}
