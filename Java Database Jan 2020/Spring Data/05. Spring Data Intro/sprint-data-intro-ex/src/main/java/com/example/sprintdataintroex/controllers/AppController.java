package com.example.sprintdataintroex.controllers;

import com.example.sprintdataintroex.constants.GlobalConstants;
import com.example.sprintdataintroex.services.CategoryService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;


    @Autowired
    public AppController(CategoryService categoryService, FileUtil fileUtil) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

//        this.categoryService.seedCategories();

    }
}
