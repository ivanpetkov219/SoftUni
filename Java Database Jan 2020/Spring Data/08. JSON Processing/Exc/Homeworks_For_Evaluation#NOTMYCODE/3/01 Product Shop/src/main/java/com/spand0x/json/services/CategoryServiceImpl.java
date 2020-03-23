package com.spand0x.json.services;


import com.spand0x.json.models.dtos.CategorySeedDto;
import com.spand0x.json.models.dtos.CategoryStatisticDto;
import com.spand0x.json.models.entities.Category;
import com.spand0x.json.repositories.CategoryRepository;
import com.spand0x.json.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategory(CategorySeedDto categorySeedDto) {
        if(this.validationUtil.isValid(categorySeedDto)){
            Category category = modelMapper.map(categorySeedDto,Category.class);
            this.categoryRepository.saveAndFlush(category);
        }else {
            this.validationUtil.violations(categorySeedDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        int randCount = random.nextInt(3);
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < randCount; i++) {
            int randNum = random.nextInt((int) this.categoryRepository.count())+1;
            Category category = this.categoryRepository.getOne((long) randNum);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public List<CategoryStatisticDto> getCategoriesStatistics() {
        List<String[]> categories = this.categoryRepository.getCategoriesStatistics();
        return categories.stream().map(c->{
            CategoryStatisticDto dto = new CategoryStatisticDto();
            dto.setCategory(c[0]);
            dto.setProductsCount(Integer.parseInt(c[1]));
            dto.setAveragePrice(new BigDecimal(c[2]));
            dto.setTotalRevenue(new BigDecimal(c[3]));
            return dto;
        }).collect(Collectors.toList());
    }

}
