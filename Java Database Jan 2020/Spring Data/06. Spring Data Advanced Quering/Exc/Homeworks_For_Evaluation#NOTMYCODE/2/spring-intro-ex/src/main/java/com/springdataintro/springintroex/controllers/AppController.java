package com.springdataintro.springintroex.controllers;

import com.springdataintro.springintroex.Services.AuthorService;
import com.springdataintro.springintroex.Services.BookService;
import com.springdataintro.springintroex.Services.CategoryService;
import com.springdataintro.springintroex.entities.Author;
import com.springdataintro.springintroex.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;


import java.io.BufferedReader;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
//        this.findAllBookAfterYear();
//        this.findAllAuthorsWithBookBefore();
//        this.getAuthorsOrderByCountOfBooks();
//        this.getGeorgePowellBooks();


        //Ex 1. Books Titles by Age Restriction

//        System.out.println("Enter age restriction: ");
//        this.bookService.getAllBooksByAgeRestriction(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //Ex 2. Golden Books

//        this.bookService.getAllBooksByEditionTypeAndCopies("gold", 5000)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //EX 3. Books by Price

//        this.bookService.getAllBooksByPriceLowerThanOrHigherThanCriteria(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
//                .forEach(b -> {
//                    System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice());
//                });


        //Ex 4.	Not Released Books

//        System.out.println("Enter year: ");
//        this.bookService.getAllBooksByReleaseDateNotInYear(
//                Integer.parseInt(this.bufferedReader.readLine()))
//                .forEach(x -> {
//                    System.out.printf("%s %n", x.getTitle());
//                });

        //Ex 5.	Books Released Before Date

//        System.out.println("Enter date: ");
//
//        this.bookService.getAllBooksReleasedBefore(bufferedReader.readLine())
//                .forEach(b -> {
//                    System.out.printf("%s %s %s%n",
//                            b.getTitle(),
//                            b.getEditionType(),
//                            b.getPrice());
//                });

        //Ex 6.	Authors Search

//        System.out.println("Enter last character of the name you want to search with: ");
//        this.authorService.findAllAuthorsByNameEnding(bufferedReader.readLine())
//                .forEach(a -> {
//                    System.out.printf("%s %s%n"
//                            , a.getFirstName()
//                            , a.getLastName());
//                });

        //Ex 7.	Books Search

//        System.out.println("Enter part of title to contain int title: ");
//
//        this.bookService.findAllByTitleContaining(bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //Ex 8.	Book Titles Search
//
//        System.out.println("Enter part of authors last name to start with: ");
//        this.bookService.findAllByAuthorLastNameStartsWith(bufferedReader.readLine())
//                .forEach(b -> {
//                    System.out.printf("%s (%s %s)%n"
//                            , b.getTitle()
//                            , b.getAuthor().getFirstName()
//                            , b.getAuthor().getLastName());
//                });

        //Ex 9.	Count Books

//        System.out.println("Enter length of the title: ");
//        int size = Integer.parseInt(bufferedReader.readLine());
//        System.out.println(this.bookService.findAllByTitleThatIsLongerThan(size));
//        System.out.printf("There are %d books with longer title than %d symbols",this.bookService.findAllByTitleThatIsLongerThan(size), size);

        //Ex 10. Total Book Copies

//        this.authorService.getAllByBookCopies()
//                .forEach(a -> {
//                    System.out.printf("%s %s - %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size());
//                });


        //Ex 11. Reduced Book

        System.out.println("Enter book title: ");

        this.bookService.findBookByTitle(bufferedReader.readLine())
                .forEach(System.out::println);

        //Ex 12. *Increase Book Copies

//        System.out.println("Enter date and number of copies: ");
//        String date = bufferedReader.readLine();
//        int numOfCopies = Integer.parseInt(bufferedReader.readLine());
//
//        int totalCopies = this.bookService.updateBookCopies(numOfCopies, date) * numOfCopies;
//        System.out.println(totalCopies);

        //Ex 13. *Remove Books
//        System.out.println("Enter number of minimum number of copies: ");
//        System.out.println(this.bookService
//                .removeBooksThatCopiesAreLessThanGivenNumber(Integer.parseInt(bufferedReader.readLine())));

        //Ex 14. *Stored Procedure

        //TODO: NOT WORKING
//        System.out.println(this.authorService.getBooksByAuthor(bufferedReader.readLine(), bufferedReader.readLine()));
//
//        ResultSet rs = this.authorService.getBooksByAuthor(bufferedReader.readLine(), bufferedReader.readLine());
//        System.out.printf("%s", rs.getString(1));

    }


//    private void findAllBookAfterYear() {
//        List<String> titles = this.bookService.findAllTitles();
//        titles.forEach(System.out::println);
//    }
//
//    private void findAllAuthorsWithBookBefore() {
//        List<String> authors = this.bookService.findAllAuthors();
//        authors.forEach(System.out::println);
//    }
//
//    private void getAuthorsOrderByCountOfBooks() {
//        List<String> authors = this.authorService.getAllAuthorsByBookCount();
//        authors.forEach(System.out::println);
//    }
//
//    private void getGeorgePowellBooks() {
//        this.bookService.findGeorgePowellBooks()
//                .forEach(System.out::println);
//    }
}
