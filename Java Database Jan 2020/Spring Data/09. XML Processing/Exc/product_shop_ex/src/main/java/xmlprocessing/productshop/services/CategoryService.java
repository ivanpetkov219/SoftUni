package xmlprocessing.productshop.services;

import xmlprocessing.productshop.models.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {

    void seedCategories(List<CategorySeedDto> categorySeedDtos);
}
