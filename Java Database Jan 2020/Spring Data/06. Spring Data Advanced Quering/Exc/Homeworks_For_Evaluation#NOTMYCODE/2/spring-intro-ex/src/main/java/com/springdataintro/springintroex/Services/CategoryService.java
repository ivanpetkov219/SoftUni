package com.springdataintro.springintroex.Services;

import com.springdataintro.springintroex.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getCategoryById(Long id);
}
