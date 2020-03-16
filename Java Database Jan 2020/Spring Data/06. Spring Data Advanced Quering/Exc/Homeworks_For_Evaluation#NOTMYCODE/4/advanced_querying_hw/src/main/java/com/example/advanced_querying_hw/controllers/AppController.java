package com.example.advanced_querying_hw.controllers;

import com.example.advanced_querying_hw.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

//        1. Books Titles by Age Restriction

//        this.bookService.getBookTitlesByAgeRestriction().forEach(System.out::println);

//        2. Golden Books

//        this.bookService.getGoldenBooksWithLessThan5000Copies().forEach(System.out::println);

//        3. Books by Price
//
//        this.bookService.getBooksWithPriceLessThan5OrMoreThan40().forEach(s
//                -> System.out.printf("%s - $%.2f\n", s.getTitle(), s.getPrice()));

//        4. Not Released Books

//        this.bookService.getBooksNotReleasedInYear().forEach(System.out::println);

//        5. Books Released Before Date

//        this.bookService.getBooksReleasedBeforeDate().forEach(s ->
//                System.out.printf("%s %s %.2f\n", s.getTitle(), s.getEditionType(), s.getPrice()));

//        6. Authors Search

//        this.authorService.getAuthorsWithFirstNameEndingWith().forEach(s ->
//                System.out.printf("%s %s\n", s.getFirstName(), s.getLastName()));

//        7. Books Search

//        this.bookService.getBooksThatContainString().forEach(System.out::println);

//        8. Book Titles Search

//        this.bookService.getBooksWithAuthorLastNameStartingWith().forEach(s -> System.out.printf("%s (%s %s)\n",
//                        s.getTitle(), s.getAuthor().getFirstName(), s.getAuthor().getLastName()));

//        9. Count Books

//        this.bookService.countBooksWithTitleLongerThan();

//        10. Total Book Copies

//        this.authorService.getAuthorsTotalCopies().forEach(System.out::println);

//        11. Reduced Book

        System.out.println(this.bookService.getBookInfoByTitle());

//        12. * Increase Book Copies

//        this.bookService.increaseBookCopiesAfterDateBy();

//        13. * Remove Books

//        this.bookService.removeBooksWithCopiesLessThan();

//        14. * Stored Procedure - TODO
    }
}
