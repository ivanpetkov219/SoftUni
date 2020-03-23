package com.spand0x.json.services;


import com.spand0x.json.models.dtos.ProductInRangeDto;
import com.spand0x.json.models.dtos.ProductSeedDto;
import com.spand0x.json.models.entities.Category;
import com.spand0x.json.models.entities.Product;
import com.spand0x.json.models.entities.User;
import com.spand0x.json.repositories.ProductRepository;
import com.spand0x.json.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ValidationUtil validationUtil, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void seedProducts(ProductSeedDto productSeedDto) {
        if (this.validationUtil.isValid(productSeedDto)) {
            Product product = this.modelMapper.map(productSeedDto, Product.class);

            User seller = this.userService.getRandomUser();
            product.setSeller(seller);

            Random random = new Random();
            int randomNum = random.nextInt(2);
            if(randomNum == 1){
                User buyer = this.userService.getRandomUser();
                product.setBuyer(buyer);
            }
            Set<Category> categories = this.categoryService.getRandomCategories();
            product.setCategories(categories);
            categories.forEach(c->c.getProducts().add(product));
            this.productRepository.save(product);
        } else {
            this.validationUtil.violations(productSeedDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }

    @Override
    public Set<ProductInRangeDto> getProductsInRange(BigDecimal min, BigDecimal max) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNull(min,max)
                .stream()
                .map(p-> {
                    ProductInRangeDto product = this.modelMapper.map(p, ProductInRangeDto.class);
                    product.setSeller(p.getSeller().getFirstName() + " " + p.getSeller().getLastName());
                    return product;
                })
                .collect(Collectors.toSet());

    }
}
