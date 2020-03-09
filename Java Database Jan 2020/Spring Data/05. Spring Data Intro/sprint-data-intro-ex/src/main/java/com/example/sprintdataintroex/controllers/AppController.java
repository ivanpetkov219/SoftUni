package com.example.sprintdataintroex.controllers;

import com.example.sprintdataintroex.entities.Book;
import com.example.sprintdataintroex.services.AuthorService;
import com.example.sprintdataintroex.services.BookService;
import com.example.sprintdataintroex.services.CategoryService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final FileUtil fileUtil;
    private final BufferedReader bufferedReader;


    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, FileUtil fileUtil1) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.fileUtil = fileUtil1;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        String welcomeInfo = "******************************************************\n" +
                "Please enter the number of the task you wish to execute:\n" +
                "Type 1 for Query #1 ,\n" +
                "Type 2 for Query #2 ,\n" +
                "Type 3 for Query #3 ,\n" +
                "Type 4 for Query #4 ,\n" +
                "Type q to exit.";
        System.out.println(welcomeInfo);

        executeAccordingToInput(bufferedReader, welcomeInfo);

    }

    private void executeAccordingToInput(BufferedReader bufferedReader, String welcomeInfo) throws IOException {
        String input = bufferedReader.readLine();
        while (!"q".equals(input)) {

            switch (input) {
                case "1":
                    System.out.println("Executing task 1...");

                    executeTask1();

                    System.out.println("Task 1 completed!");
                    break;
                case "2":
                    System.out.println("Executing task 2...");

//                    executeTask2();

                    System.out.println("Task 2 completed!");
                    break;
                case "3":
                        System.out.println("Executing task 3...");

                        executeTask3();

                        System.out.println("Task 3 completed!");
                    break;
                case "4":
                    System.out.println("Executing task 4...");

//                    executeTask4();

                    System.out.println("Task 4 completed!");
                    break;
                default:
                    System.out.printf("Input must be a number between 1 and 4...%nPlease try again!");
                    break;

            }

            System.out.println();
            System.out.println("*****************************");
            System.out.println(welcomeInfo);
            input = bufferedReader.readLine();
        }

    }

    private void executeTask4() {
        this.bookService.getAllBooksByAuthor().stream()
                .forEach(b-> {
                    System.out.printf("%s %s %d", b.getTitle(), b.getReleaseDate(), b.getCopies());
                });

    }

    private void executeTask3() {
        this.authorService.getAllAuthorsByCountOfBooksDesc().stream()
                .forEach(a-> {
                    System.out.printf("%s %s %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size());
                });

    }

    private void executeTask2() {

        this.authorService.getAllAuthorsWithBookBefore1990()
                .stream().forEach(a-> {
            System.out.printf("%s %s%n", a.getFirstName(), a.getLastName());
        });
    }

    private void executeTask1() {

    this.bookService.getAllBooksReleasedAfterYear2000().stream()
            .forEach(b-> {
                System.out.printf("%s%n", b.getTitle());
            });


    }
}
