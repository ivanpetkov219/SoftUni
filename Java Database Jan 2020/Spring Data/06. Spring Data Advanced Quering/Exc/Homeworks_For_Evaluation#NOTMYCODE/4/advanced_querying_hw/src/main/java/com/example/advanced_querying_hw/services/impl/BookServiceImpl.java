package com.example.advanced_querying_hw.services.impl;

import com.example.advanced_querying_hw.entities.*;
import com.example.advanced_querying_hw.repositories.BookRepository;
import com.example.advanced_querying_hw.services.*;
import com.example.advanced_querying_hw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.advanced_querying_hw.constants.GlobalConstants.*;
import static com.example.advanced_querying_hw.entities.EditionType.GOLD;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRep;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;
    private final BufferedReader rd;

    @Autowired
    public BookServiceImpl(BookRepository bookRep, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRep = bookRep;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
        this.rd = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookRep.count() != 0)
            return;

        List<String> fileContent = this.fileUtil.readFileData(BOOKS_FILE_PATH);

        fileContent.forEach(s -> {
            String[] params = s.split("\\s+");

            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            LocalDate releaseDate = LocalDate.parse(params[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            int copies = Integer.parseInt(params[2]);
            BigDecimal price = new BigDecimal(params[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            String title = this.getTitle(params);
            Set<Category> categories = getRandomCategories();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);

            this.bookRep.saveAndFlush(book);
        });
    }

    @Override
    public List<String> getBookTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction (case insensitive):");
        String rest = this.rd.readLine().toUpperCase();

        if (rest.equals("MINOR") || rest.equals("TEEN") || rest.equals("ADULT"))
            return this.bookRep.findByAgeRestriction(AgeRestriction.valueOf(rest)).stream()
                    .map(Book::getTitle)
                    .collect(Collectors.toList());
        else return List.of("Invalid Age Restriction!");
    }

    @Override
    public List<String> getGoldenBooksWithLessThan5000Copies() {
        return this.bookRep.findByEditionTypeAndCopiesLessThan(GOLD, 5000).stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksWithPriceLessThan5OrMoreThan40() {
        return this.bookRep.findByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }

    @Override
    public List<String> getBooksNotReleasedInYear() throws IOException {
        System.out.println("Enter year:");
        int year = Integer.parseInt(rd.readLine());

        return this.bookRep.getBooksByReleaseDateNotYear(year).stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksReleasedBeforeDate() throws IOException {
        System.out.println("Enter date (dd-MM-yyyy):");
        String date = rd.readLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Book> list = this.bookRep.getByReleaseDateBefore(localDate);
        if (list.isEmpty()) System.out.println("No books found released before " + date);
        return list;
    }

    @Override
    public List<String> getBooksThatContainString() throws IOException {
        System.out.println("Enter letter/s:");
        String str = rd.readLine();
        String s = "%" + str.toLowerCase() + "%";
        List<String> list = this.bookRep.getByTitleContaining(s).stream().map(Book::getTitle).collect(Collectors.toList());
        if (list.isEmpty()) System.out.println("No book found containing " + str);
        return list;
    }

    @Override
    public List<Book> getBooksWithAuthorLastNameStartingWith() throws IOException {
        System.out.println("Enter letter/s:");
        String str = rd.readLine();
        List<Book> list = this.bookRep.getByAuthorLastNameStartingWith(str.toLowerCase() + "%");
        if (list.isEmpty()) System.out.println("No book found with author's last name starting with " + str);
        return list;
    }

    @Override
    public void countBooksWithTitleLongerThan() throws IOException {
        System.out.println("Enter number:");
        int length = Integer.parseInt(rd.readLine());
        int num = this.bookRep.countByTitleLongerThan(length);
        System.out.println(num);
        System.out.printf("There are %d books with longer title than %d symbols\n", num, length);
    }

    @Override
    public String getBookInfoByTitle() throws IOException {
        System.out.println("Enter book title:");
        String title = rd.readLine();
        Book b = this.bookRep.getBookByTitle(title);
        return b == null ? "No book found with title " + title : String.format("%s %s %s %.2f",
                b.getTitle(), b.getEditionType().name(), b.getAgeRestriction().name(), b.getPrice());
    }

    @Override
    public void increaseBookCopiesAfterDateBy() throws IOException {
        System.out.println("Enter date (dd MMM yyyy):");
        String date = rd.readLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println("Enter number of copies:");
        int copies = Integer.parseInt(rd.readLine());
        int books = this.bookRep.updateBooksAfterDateByNumber(localDate, copies);
        System.out.println(books * copies);
        System.out.printf("%d books are released after %s, so total of %d book copies were added\n",
                books, date, books * copies);
    }

    @Override
    public void removeBooksWithCopiesLessThan() throws IOException {
        System.out.println("Enter copies number:");
        int num = Integer.parseInt(rd.readLine());
        int books = this.bookRep.removeBooksWithCopiesLessThan(num);
        System.out.println(books);
        System.out.printf("%d books with copies less than %d were removed\n", books, num);
    }

    //////////

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById(((long) categoryId)));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < params.length; i++)
            sb.append(params[i]).append(" ");

        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAuthorsCount()) + 1;

        return this.authorService.findAuthorById(((long) randomId));
    }
}
