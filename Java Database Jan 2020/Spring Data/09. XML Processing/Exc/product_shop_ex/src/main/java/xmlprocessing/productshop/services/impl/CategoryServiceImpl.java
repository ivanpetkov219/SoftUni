package xmlprocessing.productshop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlprocessing.productshop.dao.CategoryRepository;
import xmlprocessing.productshop.models.dtos.CategorySeedDto;
import xmlprocessing.productshop.models.entities.Category;
import xmlprocessing.productshop.services.CategoryService;

import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, Random random) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.random = random;
    }


    @Override
    public void seedCategories(List<CategorySeedDto> categorySeedDtos) {



    }
}
