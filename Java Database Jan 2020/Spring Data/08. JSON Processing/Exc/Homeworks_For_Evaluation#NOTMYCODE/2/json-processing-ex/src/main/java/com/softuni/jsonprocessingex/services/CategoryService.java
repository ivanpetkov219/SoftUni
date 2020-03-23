package com.softuni.jsonprocessingex.services;

import com.softuni.jsonprocessingex.models.dtos.CategoryByProductsCountDto;
import com.softuni.jsonprocessingex.models.dtos.CategorySeedDto;
import com.softuni.jsonprocessingex.models.entities.Category;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategories();

    List<CategoryByProductsCountDto> getAllCategoriesByProductsCount();
}
