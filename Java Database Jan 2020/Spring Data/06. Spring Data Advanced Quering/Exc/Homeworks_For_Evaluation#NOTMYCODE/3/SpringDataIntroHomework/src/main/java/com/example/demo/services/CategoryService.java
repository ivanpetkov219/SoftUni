package com.example.demo.services;

import com.example.demo.models.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    void save(Category category);
}
