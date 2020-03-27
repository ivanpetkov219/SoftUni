package com.softuni.xmlparsingexerciseone.controllers;

import com.softuni.xmlparsingexerciseone.services.CategoryService;
import com.softuni.xmlparsingexerciseone.services.ProductService;
import com.softuni.xmlparsingexerciseone.services.UserService;
import com.softuni.xmlparsingexerciseone.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppController implements CommandLineRunner {
   private final UserService userService;
   private final CategoryService categoryService;
   private final ProductService productService;




   @Autowired
    public AppController(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
       this.categoryService = categoryService;
       this.productService = productService;

   }

    @Override
    public void run(String... args) throws Exception {

//       seed the data
//        this.userService.seedUsers();
//        this.categoryService.seedCategories();
//        this.productService.seedProducts();


        //Всеки път когато пробваш задача,изтрии съдържанието на result.xml
        //firstExercise
//       this.productService.firstExercise(500,1000);

        //secondExercise

//        this.userService.secondExercise();

        //thirdExercise
//        this.categoryService.thirdExercise();

            //еxerciseFour

        this.userService.exerciseFour();


    }




}
