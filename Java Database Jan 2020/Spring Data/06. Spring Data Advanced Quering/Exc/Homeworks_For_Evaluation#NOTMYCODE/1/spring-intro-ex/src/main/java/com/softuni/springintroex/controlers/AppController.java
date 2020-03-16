package com.softuni.springintroex.controlers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.enums.AgeRestriction;
import com.softuni.springintroex.enums.EditionType;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        System.out.println("For Spring-Intro-Ex press 1, for Spring-Advanced-Query-Ex press 2:");
        int input = Integer.parseInt(this.reader.readLine());

        switch (input) {
            case 1:
                this.springIntroExercise();
                break;
            case 2:
                this.springAdvancedQueryExercise();
                break;
            default:
                System.out.println("Invalid number, try again!");
        }
    }

    private void springAdvancedQueryExercise() throws IOException {
        System.out.println("----->>> Enter number from 1 to 11 to view result from queries:");
        String input = this.reader.readLine();

        while (!input.equals("End")) {
            int number = Integer.parseInt(input);

            switch (number) {
                case 0:
                    this.information();
                    break;
                case 1:
                    this.booksTitlesByAgeRestriction();
                    break;
                case 2:
                    this.findAllBooksByEditionTypeAndCopiesLessThen5000();
                    break;
                case 3:
                    this.findAllBooksWithPriceLowerAndGreaterThanGiven();
                    break;
                case 4:
                    this.findAllBooksThatAreBeforeAndAfterYearInGivenInput();
                    break;
                case 5:
                    this.findAllBooksWithReleaseDateBeforeGiven();
                    break;
                case 6:
                    this.findAllAuthorsWhereFirstNameEndsWithGivenPattern();
                    break;
                case 7:
                    this.findAllBooksByPattern();
                    break;
                case 8:
                    this.findBooksByGivenDelimiter();
                    break;
                case 9:
                    this.countBooksByGivenLength();
                    break;
                case 10:
                    this.totalBookCopiesByGivenAuthorName();
                    break;
                case 11:
                    this.findBookByGivenTitle();
                    break;
                default:
                    System.out.println("---->> Input is not between 1 and 11! For exit write \"End\". For Information enter 0.");
            }
            System.out.println();
            System.out.println("---->>> For exit write \"End\"! For information enter 0.");
            input = this.reader.readLine();
        }
    }

    private void information() {
        System.out.println("If you want to exit the application -> write \"End\"");
        System.out.println("Enter number from 1 to 11 to view the results from Spring-Advanced-Query-Exercise");
    }

    private void findBookByGivenTitle() throws IOException {
        System.out.println("Enter book title:");
        String title = this.reader.readLine();
        Book book = this.bookService.getBookByGivenTitle(title);
        System.out.printf("-->> Book information: %s %s %s %.2f%n",
                book.getTitle(), book.getEditionType().name(),
                book.getAgeRestriction().name(), book.getPrice());
    }

    private void totalBookCopiesByGivenAuthorName() throws IOException {
        System.out.println("Enter author full name:");
        String fullName = this.reader.readLine();
        List<Book> books = this.bookService.getBooksByGivenAuthorName(fullName);
        int count = 0;
        for (Book book : books) {
            count += book.getCopies();
        }
        if (books.size() != 0) {
            System.out.printf("%s - %d%n", fullName, count);
        }
    }

    private void countBooksByGivenLength() throws IOException {
        System.out.println("Enter length:");
        int length = Integer.parseInt(this.reader.readLine());
        int count = this.bookService
                .getAllBooksByGivenCharacterLength(length)
                .size();
        System.out.println("-->> Count of books are: " + count);
    }

    private void findBooksByGivenDelimiter() throws IOException {
        System.out.println("Enter pattern:");
        String input = this.reader.readLine();
        this.bookService.getAllBooksByAuthorLastNameContainsGivenStringPattern(input)
                .forEach(book -> System.out.printf("%s (%s %s)%n",
                        book.getTitle(), book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()));
    }

    private void findAllBooksByPattern() throws IOException {
        System.out.println("Enter pattern:");
        String pattern = this.reader.readLine();
        this.bookService.getAllBooksThatContainsGivenPattern(pattern)
                .forEach(book -> System.out.printf("%s%n", book.getTitle()));
    }

    private void findAllAuthorsWhereFirstNameEndsWithGivenPattern() throws IOException {
        System.out.println("Enter pattern:");
        String pattern = this.reader.readLine();
        this.authorService.findAllAuthorsWhereFirstNameEndsWith(pattern)
                .forEach(author -> System.out.printf("%s %s%n",
                        author.getFirstName(), author.getLastName()));
    }

    private void findAllBooksWithReleaseDateBeforeGiven() throws IOException {
        System.out.println("Enter date in format dd-mm-yyyy:");
        String input = this.reader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        this.bookService.getAllBooksByReleaseDateBeforeGiven(date)
                .forEach(book -> System.out.printf("%s %s %.2f%n",
                        book.getTitle(), book.getEditionType().name(),
                        book.getPrice()));
    }

    private void findAllBooksThatAreBeforeAndAfterYearInGivenInput() throws IOException {
        System.out.println("Enter year:");
        int year = Integer.parseInt(this.reader.readLine());
        LocalDate before = LocalDate.of(year, 1, 1);
        LocalDate after = LocalDate.of(year, 12, 31);
        this.bookService.getAllBooksThatAreNotInGivenInput(before, after);
    }

    private void findAllBooksWithPriceLowerAndGreaterThanGiven() throws IOException {
        System.out.println("Enter lower price:");
        BigDecimal lower = new BigDecimal(this.reader.readLine());
        System.out.println("Enter greater price:");
        BigDecimal greater = new BigDecimal(this.reader.readLine());
        this.bookService.getAllBooksByPriceLowerAndHigherThanGiven(lower, greater)
                .forEach(book -> System.out.printf("%s - $%.2f%n",
                        book.getTitle(), book.getPrice()));
    }

    private void findAllBooksByEditionTypeAndCopiesLessThen5000() throws IOException {
        System.out.println("Enter edition type:");
        String editionType = this.reader.readLine().toUpperCase();
        int copies = 5000;
        this.bookService.getAllBooksByEditionTypeAndLessThan5000Copies(EditionType.valueOf(editionType), copies)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void booksTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction:");
        String ageRestriction = this.reader.readLine().toUpperCase();
        this.bookService
                .getAllBooksByAgeRestriction(AgeRestriction.valueOf(ageRestriction))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void springIntroExercise() throws IOException {
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
