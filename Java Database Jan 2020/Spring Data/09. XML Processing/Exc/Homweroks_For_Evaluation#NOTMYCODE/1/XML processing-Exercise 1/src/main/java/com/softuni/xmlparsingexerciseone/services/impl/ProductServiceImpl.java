package com.softuni.xmlparsingexerciseone.services.impl;



import com.softuni.xmlparsingexerciseone.dtos.firstExerciseDtos.ProductDto;
import com.softuni.xmlparsingexerciseone.dtos.firstExerciseDtos.RootProducts;
import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportProductDto;
import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportProductsDto;
import com.softuni.xmlparsingexerciseone.entities.Product;
import com.softuni.xmlparsingexerciseone.repositories.ProductRepository;
import com.softuni.xmlparsingexerciseone.services.CategoryService;
import com.softuni.xmlparsingexerciseone.services.ProductService;
import com.softuni.xmlparsingexerciseone.services.UserService;
import com.softuni.xmlparsingexerciseone.utils.ValidationUtil;
import com.softuni.xmlparsingexerciseone.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMap;
    private final XMLParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ValidationUtil validationUtil, UserService userService, CategoryService categoryService, ModelMapper modelMap, XMLParser xmlParser) {
        this.productRepository = productRepository;

        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMap = modelMap;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedProducts() throws  JAXBException {
        ImportProductsDto importProductsDto=this.xmlParser.read(ImportProductsDto.class,"src/main/resources/files/products.xml");

        for (ImportProductDto productDto : importProductsDto.getProducts()) {

            Product product =this.modelMap.map(productDto,Product.class);

            if (validationUtil.isValid(product)) {
                product.setBuyer(this.userService.getRandomBuyer());
                product.setSeller(this.userService.getRandomSeller());
                product.setCategories(this.categoryService.generateRandomCategories());
                this.productRepository.save(product);
            } else {
                validationUtil.getViolations(product);

            }
        }

    }

    @Override
    public void firstExercise(double low, double high) throws JAXBException {

        Set<Product> result = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(low, high);
        Set<ProductDto>productDtos=result.stream().map(p->this.modelMap.map(p,ProductDto.class)).collect(Collectors.toSet());
        RootProducts rootProducts=new RootProducts();
        rootProducts.setProducts(productDtos);


        this.xmlParser.write(rootProducts,"src/main/resources/result.xml");

    }
}
