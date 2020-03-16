package com.example.advanced_querying_hw.services;

import com.example.advanced_querying_hw.entities.Category;

import java.io.IOException;

public interface CategoryService {

    void seedCategories() throws IOException;

    Category getCategoryById(Long id);
}
