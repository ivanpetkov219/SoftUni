package com.softuni.xmlparsingexerciseone.services.impl;



import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportCategoriesDto;
import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportCategoryDto;
import com.softuni.xmlparsingexerciseone.dtos.thirdExerciseDto.CategoryDtoThirdExercise;
import com.softuni.xmlparsingexerciseone.dtos.thirdExerciseDto.RootCategoriesDto;
import com.softuni.xmlparsingexerciseone.entities.Category;
import com.softuni.xmlparsingexerciseone.repositories.CategoryRepository;
import com.softuni.xmlparsingexerciseone.services.CategoryService;
import com.softuni.xmlparsingexerciseone.utils.ValidationUtil;
import com.softuni.xmlparsingexerciseone.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMap;
    private final XMLParser xmlParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMap, XMLParser xmlParser) {
        this.categoryRepository = categoryRepository;

        this.validationUtil = validationUtil;
        this.modelMap = modelMap;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCategories() throws  JAXBException {

        ImportCategoriesDto categoriesDto=this.xmlParser.read(ImportCategoriesDto.class,"src/main/resources/files/categories.xml");
        System.out.println();

        for (ImportCategoryDto importCategoryDto : categoriesDto.getCategories()) {
            Category category=this.modelMap.map(importCategoryDto,Category.class);
            if(validationUtil.isValid(category)){
                this.categoryRepository.save(category);
            }else{
                validationUtil.getViolations(category)
                .forEach(e-> System.out.println(e.getMessage()));
            }
        }
//
    }

    @Override
    public Set<Category> generateRandomCategories() {
        Random random=new Random();
        Set<Category>categories = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            int randomInt = random.nextInt((int)this.categoryRepository.count())+1;
            Category randomCat=this.categoryRepository.findById((long)randomInt).get();
            categories.add(randomCat);
        }
        return categories;
    }

    @Override
    public void thirdExercise() throws JAXBException {
        List<Category> categories = this.categoryRepository.thirdExercise();

        List<CategoryDtoThirdExercise>categoryDtoThirdExercises=new ArrayList<>();

        for (Category category : categories) {
            String name=category.getName();
            int count =category.getProducts().size();
            double avg=category.getProducts().stream().map(e->e.getPrice()).mapToDouble(Double::doubleValue).average().getAsDouble();
            double sum=category.getProducts().stream().map(e->e.getPrice()).mapToDouble(Double::doubleValue).sum();
            categoryDtoThirdExercises.add(new CategoryDtoThirdExercise(name,count,avg,sum));

        }
        RootCategoriesDto rootCategoriesDto=new RootCategoriesDto(categoryDtoThirdExercises);
        this.xmlParser.write(rootCategoriesDto,"src/main/resources/result.xml");


    }
}
