package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.enums.AgeRestriction;
import com.softuni.springintroex.enums.EditionType;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil
                .readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(row -> {
                    String[] parameters = row.split("\\s+");
                    Author author = this.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(parameters[0])];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(parameters[1], formatter);
                    int copies = Integer.parseInt(parameters[2]);
                    BigDecimal price = new BigDecimal(parameters[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(parameters[4])];
                    String title = this.getTitle(parameters);
                    Set<Category> categories = this.setRandomCategories();

                    Book book = new Book(author, editionType, releaseDate,
                            copies, price, ageRestriction, title, categories);

                    this.bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public List<Book> getAllBooksBefore1990() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("01/01/1990", formatter);
        return this.bookRepository.findBookByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> getAllBooksFromAuthor() {
        Author author = this.authorService.findAuthorByName();
        return this.bookRepository.findAllByAuthorOrderByReleaseDateDesc(author);
    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/2000", formatter);
        return this.bookRepository.findAllByReleaseDateAfter(releaseDate);
    }

    private Set<Category> setRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();

        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            result.add(this.categoryService.getCategoryById((long) i));
        }

        return result;
    }

    private String getTitle(String[] parameters) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < parameters.length; i++) {
            sb.append(parameters[i])
                    .append(" ");
        }

        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}
