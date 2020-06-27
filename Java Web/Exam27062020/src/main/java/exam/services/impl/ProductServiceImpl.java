package exam.services.impl;

import exam.models.entities.Category;
import exam.models.entities.Product;
import exam.models.service.ProductServiceModel;
import exam.models.view.ProductViewModel;
import exam.repositories.ProductRepository;
import exam.services.CategoryService;
import exam.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);

        Category category = this.categoryService.findByCategoryName(productServiceModel.getCategory().
                getCategoryName());

        product.setCategory(category);

        this.productRepository.saveAndFlush(product);

    }

    @Override
    public List<ProductViewModel> findAllItems() {

        return this.productRepository.findAll().stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);
                    productViewModel.setImageUrl(String.format("/img/%s.png",
                            product.getCategory().getCategoryName().name().toLowerCase()));

                    return productViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll(String id) {
        this.productRepository.deleteAll();
    }


}
