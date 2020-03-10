package com.softuni.springintroex.controlers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private Comparator<Book> comparator = Comparator.comparing(Book::getTitle);

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private BufferedReader reader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        this.reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println();
        System.out.println("----->>> Enter number from 1 to 4 to view result from queries:");
        String input = this.reader.readLine();

        while (!input.equals("End")) {
            int number = Integer.parseInt(input);

            switch (number) {
                case 1:
                    this.findAllBooksAfter2000AndPrintTheirTitles();
                    break;
                case 2:
                    this.findAllAuthorsWithBooksBefore1990AndPrintNames();
                    break;
                case 3:
                    this.findAllAuthorsByCountOfTheirBooksAndPrintThem();
                    break;
                case 4:
                    this.findAllBooksFromGeorgePowell();
                    break;
                default:
                    System.out.println("---->> Input is not between 1 and 4! For exit write \"End\"");
            }
            System.out.println();
            System.out.printf("---->> To get all books after 2000 -> press 1%n" +
                    "To get all authors with books before 1990 -> press 2%n" +
                    "To get all authors by number of their books -> press 3%n" +
                    "To get all books from George Powell -> press 4%n");
            System.out.println("---->>> For exit write \"End\"!");
            input = this.reader.readLine();
        }
    }

    private void findAllBooksFromGeorgePowell() {
        List<Book> books = this.bookService.getAllBooksFromAuthor();
        books.sort(comparator);
        String bookTitle = "Book Title";
        String releaseDate = "Release Date";
        String bookCopies = "Book Copies";
        System.out.printf("| %-30s | %-30s | %-30s |",
                bookTitle, releaseDate, bookCopies);
        System.out.println();
        books.forEach(book -> System.out.printf("| %-30s | %-30s | %-30d |%n",
                book.getTitle(),
                book.getReleaseDate().toString(),
                book.getCopies()));
    }

    private void findAllAuthorsByCountOfTheirBooksAndPrintThem() {
        List<Author> authors = this.authorService.findAuthorsOrderedByCountOfBooks();
        String firstName = "First Name";
        String lastName = "Last Name";
        String bookCount = "Book Count";

        System.out.println();
        System.out.printf("| %-10s | %-10s | %-10s |%n", firstName,
                lastName, bookCount);
        System.out.println();
        authors.forEach(author -> System.out.printf("| %-10s | %-10s | %-10d |%n",
                author.getFirstName(), author.getLastName(),
                author.getBooks().size()));
    }

    private void findAllAuthorsWithBooksBefore1990AndPrintNames() {
        List<Book> books = this.bookService.getAllBooksBefore1990();
        System.out.println("Authors full names:");
        books.forEach(book -> System.out.printf("%s %s%n",
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()));
    }

    private void findAllBooksAfter2000AndPrintTheirTitles() {
        List<Book> books = this.bookService.getAllBooksAfter2000();
        System.out.println("Book titles:");
        books.forEach(book -> System.out.printf("%s%n", book.getTitle()));
    }
}
