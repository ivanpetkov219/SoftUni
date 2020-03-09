package com.example.sprintdataintroex.services;

import com.example.sprintdataintroex.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getRandomCategoryById(Long id);

    int getAllCategoriesCount();
}
