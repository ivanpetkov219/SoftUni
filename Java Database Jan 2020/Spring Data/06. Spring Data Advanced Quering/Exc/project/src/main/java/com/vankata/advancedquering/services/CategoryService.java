package com.vankata.advancedquering.services;



import com.vankata.advancedquering.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getRandomCategoryById(Long id);

    int getAllCategoriesCount();
}
