package com.vankata.advancedquering.services.impl;


import com.vankata.advancedquering.constants.GlobalConstants;
import com.vankata.advancedquering.entities.Category;
import com.vankata.advancedquering.repositories.CategoryRepository;
import com.vankata.advancedquering.services.CategoryService;
import com.vankata.advancedquering.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

@Service
@Transactional
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
        if (this.categoryRepository.count() != 0) { //this is the easy way
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent).forEach(e ->
        {
            Category category = new Category(e);
            this.categoryRepository.saveAndFlush(category);
        });
    }

    @Override
    public Category getRandomCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public int getAllCategoriesCount() {
        return (int) this.categoryRepository.count();
    }
}
