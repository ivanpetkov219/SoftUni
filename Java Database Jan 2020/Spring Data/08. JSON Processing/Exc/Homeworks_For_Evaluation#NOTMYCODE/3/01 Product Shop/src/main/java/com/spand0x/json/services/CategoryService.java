package com.spand0x.json.services;

import com.spand0x.json.models.dtos.CategorySeedDto;
import com.spand0x.json.models.dtos.CategoryStatisticDto;
import com.spand0x.json.models.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategory(CategorySeedDto categorySeedDto);

    Set<Category> getRandomCategories();

    List<CategoryStatisticDto> getCategoriesStatistics();


}
