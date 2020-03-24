package xmlprocessing.productshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xmlprocessing.productshop.constants.GlobalConstants;
import xmlprocessing.productshop.models.dtos.CategoryRootSeedDto;
import xmlprocessing.productshop.services.CategoryService;
import xmlprocessing.productshop.utils.ValidatorUtil;
import xmlprocessing.productshop.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static xmlprocessing.productshop.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, XmlParser xmlParser, ValidatorUtil validatorUtil, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run(String... args) throws Exception {

        String welcomeInfo = "******************************************************\n" +
                "Please enter the number of the task you wish to execute:\n" +
                "Type \"seed\" to seed the Database ,\n" +
                "Type 1 for Query #1 ,\n" +
                "Type 2 for Query #2 ,\n" +
                "Type 3 for Query #3 ,\n" +
                "Type 4 for Query #4 ,\n" +
                "Type q to exit.";
//        System.out.println(welcomeInfo);
        System.out.println();
        System.out.println("*****************************");
        System.out.println("Прощавай, но домашното не е готово. Гърмеше ми постоянно на сийдването на базата и нямах време" +
                "да го фиксна. Важното е да сме живи и здрави ;)");
        System.out.println("*****************************");

        executeAccordingToInput(bufferedReader, welcomeInfo);

    }

    private void executeAccordingToInput(BufferedReader bufferedReader, String welcomeInfo) throws IOException, JAXBException {
        String input = bufferedReader.readLine();
        while (!"q".equals(input)) {

            switch (input) {
                case "1":
                    System.out.println("Executing task 1...");

//                    executeTask1();

                    System.out.println("Task 1 completed!");
                    break;
                case "2":
                    System.out.println("Executing task 2...");

//                    executeTask2();

                    System.out.println("Task 2 completed!");
                    break;
                case "3":
                    System.out.println("Executing task 3...");

//                    executeTask3();

                    System.out.println("Task 3 completed!");
                    break;
                case "4":
                    System.out.println("Executing task 4...");

//                    executeTask4();

                    System.out.println("Task 4 completed!");
                    break;
                case "seed":
                    System.out.println("Seeding database...Please wait.");

                    seedDatabase();

                    System.out.println("Seeding completed");
                    break;

                default:
                    System.out.printf("Input must be a number between 1 and 11...%nPlease try again!");
                    break;

            }

            System.out.println();
            System.out.println("*****************************");
            System.out.println(welcomeInfo);
            input = bufferedReader.readLine();
        }

    }

    private void seedDatabase() throws JAXBException, FileNotFoundException {

//        CategoryRootSeedDto categoryRootSeedDto = this.xmlParser
//                .convertFromFile(CATEGORIES_FILE_PATH, CategoryRootSeedDto.class);
//
//        this.categoryService.seedCategories(categoryRootSeedDto.getCategories());

//        this.categoryService.seedCategories();



    }

}

