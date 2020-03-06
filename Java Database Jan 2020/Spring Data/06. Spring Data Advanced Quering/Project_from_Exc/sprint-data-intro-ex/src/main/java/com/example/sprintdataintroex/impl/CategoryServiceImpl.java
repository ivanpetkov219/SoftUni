package com.example.sprintdataintroex.impl;

import com.example.sprintdataintroex.constants.GlobalConstants;
import com.example.sprintdataintroex.entities.Category;
import com.example.sprintdataintroex.repositories.CategoryRepository;
import com.example.sprintdataintroex.services.CategoryService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        //TODO: check if the category already exists in the DB
        if(this.categoryRepository.count() != 0){ //this is the easy way
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent).forEach( e ->
        {
            Category category = new Category(e);
            this.categoryRepository.saveAndFlush(category);
            });


    }
}
