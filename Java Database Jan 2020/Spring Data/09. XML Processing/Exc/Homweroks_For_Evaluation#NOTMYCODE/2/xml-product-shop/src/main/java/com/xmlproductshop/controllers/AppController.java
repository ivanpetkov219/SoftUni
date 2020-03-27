package com.xmlproductshop.controllers;

import com.xmlproductshop.dtos.CategorySeedRootDto;
import com.xmlproductshop.dtos.ProductSeedRootDto;
import com.xmlproductshop.dtos.ProductViewRootDto;
import com.xmlproductshop.dtos.UserSeedRootDto;
import com.xmlproductshop.repositories.CategoryRepository;
import com.xmlproductshop.repositories.ProductRepository;
import com.xmlproductshop.service.CategoryService;
import com.xmlproductshop.service.ProductService;
import com.xmlproductshop.service.UserService;
import com.xmlproductshop.utils.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static com.xmlproductshop.constant.GlobalConstants.*;

@Controller
public class AppController implements CommandLineRunner {

    private final XmlParser xmlParser;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;


    public AppController(XmlParser xmlParser, CategoryRepository categoryRepository, ProductRepository productRepository, UserService userService, CategoryService categoryService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @Override
    public void run(String... args) throws Exception {

        //this.seedUsers();
        //this.seedCategories();
        //this.seedProducts();
        this.ex1();
    }

    private void ex1() throws JAXBException {
        List<ProductViewRootDto> productViewRootDto=
                this.productService
                        .getAvailableProductsInPriceRange(BigDecimal.valueOf(500),BigDecimal.valueOf(1000));
    this.xmlParser.marshalToFile("src/main/resources/files/ex1.xml",ProductViewRootDto.class);
    }

    private void seedProducts() throws JAXBException, FileNotFoundException {
        ProductSeedRootDto productSeedRootDto = this.xmlParser
                .unmarshalFromFile(PRODUCTS_FILE_PATH,ProductSeedRootDto.class);
        this.productService.seedProduct(productSeedRootDto.getProducts());
    }

    private void seedUsers() throws JAXBException, FileNotFoundException {
        UserSeedRootDto userSeedRootDto = this.xmlParser
                .unmarshalFromFile(USERS_FILE_PATH,UserSeedRootDto.class);

        this.userService.seedUser(userSeedRootDto.getUsers());
    }

    private void seedCategories() throws JAXBException, FileNotFoundException {
        CategorySeedRootDto categorySeedRootDto = this.xmlParser
                .unmarshalFromFile(CATEGORIES_FILE_PATH,CategorySeedRootDto.class);

        this.categoryService.seedCategory(categorySeedRootDto.getCategories());
    }
}
