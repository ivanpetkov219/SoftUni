package com.xmlproductshop.service;

import com.xmlproductshop.dtos.CategorySeedDto;
import com.xmlproductshop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    void seedCategory(List<CategorySeedDto> categorySeedDtos);

    List<Category> getRandomCategory();

}
