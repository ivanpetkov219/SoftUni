package com.example.demo.init;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.models.Enums.AgeRestriction;
import com.example.demo.models.Enums.Edition;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ConsoleRunner(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //TEST FUNCTIONS ONE BY ONE BY UNCOMMENTING THEM
        //SEED DATABASE BEFORE TESTING
//        seedDatabase();
        //1. Books Titles by Age Restriction
//        printTitlesByAgeRestriction();

        //2. Golden Books
//        printGoldEditionTitlesWithLessThanFiveThousandCopies();

        //3. Books by Price
//        printBooksWithPriceLowerThanFiveAndHigherThanForty();

        //4. Not Released Books
//        printBooksNotReleasedInYear();

        //5. Books Released Before Date
//        printBooksBeforeReleaseDate();

        //6. Authors Search
//        printAuthorsWithFirstNameEndingWith();

        //7. Books Search
//        printBooksWithTitleContaining();

        //8. Book Titles Search
//        printBooksWithAuthorsLastName();

        //9. Count Books
//        printCountOfBooksWithTitleLongerThan();

        //10. Total Book Copies
//        printTotalCopiesByAuthor();

        //11. Reduced Book
        printInfoForBookByTitle();

    }



    private void printInfoForBookByTitle() throws IOException {
        System.out.println("Enter title: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String title = input.readLine();

        Set<Book> books = bookRepository.getBookByTitle(title);
        for (Book book : books) {
            System.out.printf("%s %s %s %.2f %n",book.getTitle(),book.getEdition(),book.getAgeRestriction(), book.getPrice());
        }
    }

    private void printTotalCopiesByAuthor() {
        Set<Book> books = bookRepository.getAllByOrderByCopiesDesc();

        for (Book book : books) {
            Author author = book.getAuthor();

            System.out.printf("%s %s - %d %n",
                    author.getFirstName(),author.getLastName(),book.getCopies());
        }
    }

    private void printCountOfBooksWithTitleLongerThan() throws IOException {
        System.out.println("Enter number: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(input.readLine());

        System.out.println(bookRepository.countAllByTitleGreaterThan(num));
    }

    private void printBooksWithAuthorsLastName() throws IOException {
        System.out.println("Enter string: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = input.readLine();

        Set<Book> books = bookRepository.getAllByAuthorWithLastName(str);
        for (Book book : books) {
            System.out.printf("%s (%s %s) %n",book.getTitle(),book.getAuthor().getFirstName(),book.getAuthor().getLastName());
        }
    }

    private void printBooksWithTitleContaining() throws IOException {
        System.out.println("Enter string: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = input.readLine();

        Set<Book> books = bookRepository.getAllByTitleContaining(str);
        for (Book book : books) {
            System.out.printf("%s %n",book.getTitle());
        }
    }

    private void printAuthorsWithFirstNameEndingWith() throws IOException {
        System.out.println("Enter string: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = input.readLine();
        Set<Author> authors = authorRepository.getAllByFirstNameEndingWith(str);

        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
    }

    private void printBooksBeforeReleaseDate() throws IOException, ParseException {
        System.out.println("Enter date: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String dateStr = input.readLine();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        Set<Book> books = bookRepository.getAllByReleaseDateBefore(date);

        for (Book book : books) {
            System.out.printf("%s %s %.2f %n",book.getTitle(),book.getEdition(),book.getPrice());
        }
    }

    private void printBooksNotReleasedInYear() throws IOException, ParseException {
        System.out.println("Enter year: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = input.readLine();
        Set<Book> books = bookRepository.getBooksByReleaseDateNot(str);

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printBooksWithPriceLowerThanFiveAndHigherThanForty() {
        Set<Book> books =
                bookRepository.getBooksByPriceLessThanOrPriceGreaterThan(new BigDecimal(5), new BigDecimal(40));

        for (Book book : books) {
            System.out.printf("%s - %.2f %n",book.getTitle(), book.getPrice());
        }
    }

    private void printGoldEditionTitlesWithLessThanFiveThousandCopies() {
        Set<Book> books = bookRepository.getBooksByEditionLikeAndCopiesLessThan(Edition.GOLD,5000);

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void printTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = input.readLine();

        Set<Book> books = bookRepository.getByAgeRestriction(str);

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }


    private void seedDatabase() throws IOException, ParseException {
        BufferedReader authorReader = new BufferedReader(new FileReader(new ClassPathResource("/authors.txt").getFile()));
        String authorLine = authorReader.readLine();
        ArrayList<Author> authors = new ArrayList<>();
        while ((authorLine = authorReader.readLine()) != null) {
            String[] authorTokens = authorLine.split("\\s+");
            String firstName = authorTokens[0];
            String lastName = authorTokens[1];
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authors.add(author);
            authorService.save(author);
        }

        BufferedReader categoryReader = new BufferedReader(new FileReader(new ClassPathResource("/categories.txt").getFile()));
        String categoryLine = categoryReader.readLine();
        ArrayList<Category> categories = new ArrayList<>();
        while ((categoryLine = categoryReader.readLine()) != null) {
            if (categoryLine.equals("")) {
                continue;
            }
            Category category = new Category();
            category.setName(categoryLine);
            categories.add(category);
            categoryService.save(category);
        }

        BufferedReader booksReader = new BufferedReader(new FileReader(new ClassPathResource("/books.txt").getFile()));
        String line = booksReader.readLine();
        while((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");

            Random random = new Random();
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            Edition editionType = Edition.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();
            Book book = new Book();
            book.setAuthor(author);
            book.setEdition(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(new HashSet<>());
            book.getCategories().add(categories.get(random.nextInt(categories.size())));
            book.getCategories().add(categories.get(random.nextInt(categories.size())));
            bookService.save(book);
    }
}}
