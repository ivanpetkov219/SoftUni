package com.example.advanced_querying_hw.services.impl;

import com.example.advanced_querying_hw.entities.Category;
import com.example.advanced_querying_hw.repositories.CategoryRepository;
import com.example.advanced_querying_hw.services.CategoryService;
import com.example.advanced_querying_hw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.advanced_querying_hw.constants.GlobalConstants.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRep;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRep, FileUtil fileUtil) {
        this.categoryRep = categoryRep;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRep.count() != 0)
            return;

        this.fileUtil.readFileData(CATEGORIES_FILE_PATH).forEach(s ->
                this.categoryRep.saveAndFlush(new Category(s)));
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRep.getOne(id);
    }
}
