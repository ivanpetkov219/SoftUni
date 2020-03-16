package com.springdataintro.springintroex.Services.ServiceImpl;

import com.springdataintro.springintroex.Services.AuthorService;
import com.springdataintro.springintroex.Services.BookService;
import com.springdataintro.springintroex.Services.CategoryService;
import com.springdataintro.springintroex.constants.GlobalConstants;
import com.springdataintro.springintroex.entities.*;
import com.springdataintro.springintroex.repositories.BookRepository;
import com.springdataintro.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");

                    Author author = this.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], formatter);

                    int copies = Integer.parseInt(params[2]);

                    BigDecimal price = new BigDecimal(params[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

                    String title = this.getTitle(params);

                    Set<Category> categories = this.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);

                    this.bookRepository.saveAndFlush(book);
                });
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();

        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {

            int categoryId = random.nextInt(8) + 1; // BECAUSE MAX CATEGORY SIZE OF THE REPOSITORY IS 8

            result.add(this.categoryService.getCategoryById((long) categoryId));
        }

        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sb.append(params[i])
                    .append(" ");
        }

        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }

    @Override
    public List<String> findAllTitles() {

        LocalDate releaseDate = LocalDate.parse("31/12/2000", DateTimeFormatter.ofPattern("d/M/yyyy"));

        return this.bookRepository
                .findAllByReleaseDateAfter(releaseDate)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthors() {
        LocalDate releaseDate = LocalDate.parse("01/01/1990", DateTimeFormatter.ofPattern("d/M/yyyy"));


        return this.bookRepository
                .findAllByReleaseDateBefore(releaseDate)
                .stream()
                .map(book -> String.format("%s %s",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findGeorgePowellBooks() {

        String firstName = "George";
        String lastName = "Powell";

        return this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
                        firstName, lastName)
                .stream()
                .map(book -> String.format("Title: %s release on %s and have %d copies",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksByAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> getAllBooksByEditionTypeAndCopies(String editionType, int copies) {

        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(
                EditionType.valueOf(editionType.toUpperCase()),
                copies
        );
    }

    @Override
    public List<Book> getAllBooksByReleaseDateNotInYear(int year) {

        LocalDate before = LocalDate.of(year, 1, 1);
        LocalDate after = LocalDate.of(year, 12, 31);

        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(before, after);
    }

    @Override
    public List<Book> getAllBooksByPriceLowerThanOrHigherThanCriteria(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice);
    }

    @Override
    public List<Book> getAllBooksReleasedBefore(String date) {

        return this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public List<Book> findAllByTitleContaining(String titlePart) {
        return this.bookRepository.findAllByTitleContaining(titlePart.toLowerCase());
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartsWith(String part) {
        return this.bookRepository.findAllByAuthorLastNameStartsWith(part);
    }

    @Override
    public int findAllByTitleThatIsLongerThan(int size) {
        return this.bookRepository.findAllByTitleThatIsLongerThan(size);
    }

    @Override
    public List<String> findBookByTitle(String title) {
        return this.bookRepository.findAllByTitle(title)
                .stream()
                .map(book -> String.format("%s %s %s %.2f",
                        book.getTitle(),
                        book.getEditionType().name(),
                        book.getAgeRestriction().name(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public int updateBookCopies(int copies, String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US));

        return this.bookRepository.updateBookCopies(copies, localDate);
    }

    @Override
    public int removeBooksThatCopiesAreLessThanGivenNumber(int copies) {
        return this.bookRepository.removeBooksThatCopiesAreLessThanGivenNumber(copies);
    }
}
