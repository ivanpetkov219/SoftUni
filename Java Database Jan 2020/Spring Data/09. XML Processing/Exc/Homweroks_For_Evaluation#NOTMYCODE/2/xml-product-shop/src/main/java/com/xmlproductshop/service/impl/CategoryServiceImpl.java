package com.xmlproductshop.service.impl;

import com.xmlproductshop.dtos.CategorySeedDto;
import com.xmlproductshop.entities.Category;
import com.xmlproductshop.repositories.CategoryRepository;
import com.xmlproductshop.service.CategoryService;
import com.xmlproductshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    public CategoryServiceImpl(ValidationUtil validationUtil, CategoryRepository categoryRepository, ModelMapper modelMapper, Random random) {
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.random = random;
    }


    @Override
    public void seedCategory(List<CategorySeedDto> categorySeedDtos) {

        categorySeedDtos
                .forEach(categorySeedDto -> {
                    if (this.validationUtil.isValid(categorySeedDto)) {
                        if (this.categoryRepository.findAllByName(categorySeedDto.getName()) == null) {
                            Category category = this.modelMapper
                                    .map(categorySeedDto, Category.class);

                            this.categoryRepository.saveAndFlush(category);
                        } else {
                            System.out.println("Already in DB");
                        }
                    } else {
                        this.validationUtil
                                .getViolations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<Category> getRandomCategory() {
        List<Category> resultList = new ArrayList<>();

        int randomCounter = random.nextInt(3) + 1;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count()) + 1;
            resultList.add(this.categoryRepository.getOne(randomId));
        }
        return resultList;
    }
}
