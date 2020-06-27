package prep.services;


import prep.models.entities.Category;
import prep.models.entities.CategoryName;
import prep.models.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryName categoryName);
}
