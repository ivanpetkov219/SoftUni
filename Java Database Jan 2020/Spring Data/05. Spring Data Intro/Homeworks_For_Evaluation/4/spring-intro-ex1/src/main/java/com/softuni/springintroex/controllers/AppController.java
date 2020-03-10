package com.softuni.springintroex.controllers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        this.authorService.seedAutors();
        this.bookService.seedBooks();





        System.out.println("Enter the query number:");
        Scanner scanner = new Scanner(System.in);
        String  query = scanner.nextLine();
        while(!"0".equals(query)) {

            switch (query) {
                case "1":
                    List<Book> books = this.bookService.getAllBooksAfter2000();
                    for (Book book : books) {
                        System.out.println(book.getTitle());
                    }
                    break;
                case "2":
                    findAutorsWithBookReleaseDateBefore1990();
                    break;
                case "3":
                    this.authorService.findAllAuthorsByCountOfBooks().forEach(a -> {
                        System.out.printf("%s %s %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size());
                        ;
                    });
                    break;
                case "4":
                    this.bookService.allBooksFromAuthorGeorgePowell();
                    break;

                default:
                    System.out.println("Wrong number!");
            }
            System.out.println();
            System.out.println("Enter 0 for exit!");
            System.out.println("Enter the query number:");
            query = scanner.nextLine();
        }
        //Ex 1
//        List<Book> books = this.bookService.getAllBooksAfter2000();
//        for (Book book : books) {
//            System.out.println(book.getTitle());
//        }
        //Ex 2
 //       findAutorsWithBookReleaseDateBefore1990();

        //Ex 3
//        this.authorService.findAllAuthorsByCountOfBooks().forEach(a->{
//            System.out.printf("%s %s %d%n",a.getFirstName(),a.getLastName(),a.getBooks().size());;
//        });


    }

    private void findAutorsWithBookReleaseDateBefore1990() {
        List<Book> books = this.bookService.findAllByReleaseDateIsBefore1990();
        Set<Author> authors = new LinkedHashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        for (Author author : authors) {
            System.out.printf("%s %s%n",author.getFirstName(),author.getLastName());
        }
    }
}
