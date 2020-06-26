package prep.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prep.models.entities.Category;
import prep.models.entities.CategoryName;
import prep.repositories.CategoryRepository;
import prep.services.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCategories() {

        if(this.categoryRepository.count() == 0){

            Arrays.stream(CategoryName.values()).forEach(categoryName -> {
                this.categoryRepository.saveAndFlush(new Category(categoryName, String.format("Description for %s", categoryName.name())));
            });
        }

    }
}
