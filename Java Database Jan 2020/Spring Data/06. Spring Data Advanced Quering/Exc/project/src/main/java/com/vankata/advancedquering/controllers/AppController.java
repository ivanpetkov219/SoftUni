package com.vankata.advancedquering.controllers;


import com.vankata.advancedquering.entities.Book;
import com.vankata.advancedquering.services.AuthorService;
import com.vankata.advancedquering.services.BookService;
import com.vankata.advancedquering.services.CategoryService;
import com.vankata.advancedquering.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Component
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final FileUtil fileUtil;
    private final BufferedReader bufferedReader;


    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, FileUtil fileUtil1, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.fileUtil = fileUtil1;
        this.bufferedReader = bufferedReader;
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
                "Type 5 for Query #5 ,\n" +
                "Type 6 for Query #6 ,\n" +
                "Type 7 for Query #7 ,\n" +
                "Type 8 for Query #8 ,\n" +
                "Type 9 for Query #9 ,\n" +
                "Type 10 for Query #10 ,\n" +
                "Type 11 for Query #11 ,\n" +
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

                    executeTask2();

                    System.out.println("Task 2 completed!");
                    break;
                case "3":
                    System.out.println("Executing task 3...");

                    executeTask3();

                    System.out.println("Task 3 completed!");
                    break;
                case "4":
                    System.out.println("Executing task 4...");

                    executeTask4();

                    System.out.println("Task 4 completed!");
                    break;
                case "5":
                    System.out.println("Executing task 5...");

                    executeTask5();

                    System.out.println("Task 5 completed!");
                    break;
                case "6":
                    System.out.println("Executing task 6...");

                    executeTask6();

                    System.out.println("Task 6 completed!");
                    break;
                case "7":
                    System.out.println("Executing task 7...");

                    executeTask7();

                    System.out.println("Task 7 completed!");
                    break;
                case "8":
                    System.out.println("Executing task 8...");

                    executeTask8();

                    System.out.println("Task 8 completed!");
                    break;
                case "9":
                    System.out.println("Executing task 9...");

                    executeTask9();

                    System.out.println("Task 9 completed!");
                    break;
                case "10":
                    System.out.println("Executing task 10...");

                    executeTask10();

                    System.out.println("Task 10 completed!");
                    break;
                case "11":
                    System.out.println("Executing task 11...");

                    executeTask11();

                    System.out.println("Task 11 completed!");
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

    private void executeTask11() throws IOException {
        System.out.println("Please enter book title: ");

        String title = this.bufferedReader.readLine();

        Book book = this.bookService.findBookDetailsByGivenTitle(title);

        System.out.printf("%s %s %s %s%n", book.getTitle(), book.getEditionType(),
                book.getAgeRestriction(), book.getPrice());
    }

    private void executeTask10() throws IOException {
        System.out.println("Please enter author's full name: ");

        String fullName = this.bufferedReader.readLine();

        int copies = this.bookService.getAllCopiesByAuthor(fullName);

        System.out.printf("%s - %d%n", fullName, copies);

    }

    private void executeTask9() throws IOException {
        System.out.println("Please enter the length of title: ");

        List<Book> books = this.bookService.getAllBooksWithTitleLongerThan(Integer.parseInt(this.bufferedReader.readLine()));

        System.out.println(books.size());

    }

    private void executeTask8() throws IOException {
        System.out.println("Please enter the char sequence you would like the name to start with: ");

        this.authorService.getAllAuthorsByFirstNameStartingWith(this.bufferedReader.readLine())
                .stream().forEach(a-> {
                    a.getBooks().stream().forEach(b-> {
                        System.out.printf("%s (%s %s)%n", b.getTitle(), a.getFirstName(), a.getLastName());
                    });
        });

    }

    private void executeTask7() throws IOException {
        System.out.println("Please enter the char sequence you would like the title to contain: ");


        this.bookService.getAllBooksWithTitleContaining(this.bufferedReader.readLine())
                .stream().forEach(b-> {
            System.out.printf("%s%n", b.getTitle());
        });

    }


    private void executeTask6() throws IOException {
        System.out.println("Please enter the char sequence you would like the name to end with: ");

        this.authorService.getAllAuthorsWithFirstNameEndingWith(this.bufferedReader.readLine())
                .stream().forEach(a -> {
            System.out.printf("%s %s%n", a.getFirstName(), a.getLastName());
        });
    }

    private void executeTask5() throws IOException {
        System.out.println("Please enter before date: ");


        this.bookService.getAllBooksReleasedBefore(this.bufferedReader.readLine())
                .stream().forEach(b-> {
            System.out.printf("%s %s %s%n", b.getTitle(), b.getEditionType(), b.getPrice());
        });

    }

    private void executeTask4() throws IOException {
        System.out.println("Please enter the year you would like to exclude: ");

        this.bookService.getAllBooksBeforeOrAfter(Integer.parseInt(this.bufferedReader.readLine()))
                .stream().forEach(b -> {
            System.out.printf("%s%n", b.getTitle());
        });

    }

    private void executeTask3() {

        this.bookService.getAllBooksWithPriceLowerThanAndHigherThan(5, 40)
                .stream().forEach(b -> {
            System.out.printf("%s - $%s%n", b.getTitle(), b.getPrice().toString());
        });
    }

    private void executeTask2() {

        this.bookService.getAllBooksOfEditionHavingLessThanCopies("Gold", 5000)
                .stream().forEach(b -> {
            System.out.printf("%s%n", b.getTitle());
        });
    }

    private void executeTask1() throws IOException {
        System.out.println("Please enter age restriction value: ");

        this.bookService.getBooksByAgeRestriction(this.bufferedReader.readLine())
                .stream()
                .forEach(b -> {
                    System.out.printf("%s%n", b.getTitle());
                });

    }
}
