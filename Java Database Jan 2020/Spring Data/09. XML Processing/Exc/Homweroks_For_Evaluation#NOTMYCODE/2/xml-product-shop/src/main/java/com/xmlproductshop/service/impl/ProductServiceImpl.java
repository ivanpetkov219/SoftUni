package com.xmlproductshop.service.impl;

import com.xmlproductshop.dtos.ProductSeedDto;
import com.xmlproductshop.dtos.ProductViewRootDto;
import com.xmlproductshop.entities.Category;
import com.xmlproductshop.entities.Product;
import com.xmlproductshop.repositories.ProductRepository;
import com.xmlproductshop.service.CategoryService;
import com.xmlproductshop.service.ProductService;
import com.xmlproductshop.service.UserService;
import com.xmlproductshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ValidationUtil validationUtil;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final Random random;

    public ProductServiceImpl(ValidationUtil validationUtil, ProductRepository productRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, Random random) {
        this.validationUtil = validationUtil;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.random = random;
    }

    @Override
    public void seedProduct(List<ProductSeedDto> productSeedDtos) {
        productSeedDtos
                .forEach(productSeedDto -> {
                    if (validationUtil.isValid(productSeedDto)) {
                            Product product = this.modelMapper
                                    .map(productSeedDto, Product.class);
                            product.setSeller(userService.getRandomUser());

                            int randomNum = random.nextInt(2);
                            if (randomNum == 1) {
                                product.setBuyer(userService.getRandomUser());
                            }
                            product.setCategories(new HashSet<>(categoryService.getRandomCategory()));

                            this.productRepository.saveAndFlush(product);

                    } else {
                        this.validationUtil
                                .getViolations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<ProductViewRootDto> getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high) {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewRootDto.class))
                .collect(Collectors.toList());
    }
}
