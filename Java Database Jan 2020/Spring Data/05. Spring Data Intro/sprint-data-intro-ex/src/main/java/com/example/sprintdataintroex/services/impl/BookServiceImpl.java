package com.example.sprintdataintroex.services.impl;

import com.example.sprintdataintroex.constants.GlobalConstants;
import com.example.sprintdataintroex.entities.*;
import com.example.sprintdataintroex.repositories.BookRepository;
import com.example.sprintdataintroex.services.AuthorService;
import com.example.sprintdataintroex.services.BookService;
import com.example.sprintdataintroex.services.CategoryService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        if (this.bookRepository.count() != 0) { //this is the easy way
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(a ->
        {
            String[] params = a.split("\\s+");

            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate = LocalDate.parse(params[1], formatter);
            int copies = Integer.parseInt(params[2]);

            BigDecimal price = new BigDecimal(params[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

            String title = getTitle(params);

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

    @Override
    public List<Book> getAllBooksReleasedAfterYear2000() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/2000", formatter);

        return this.bookRepository
                .findAllByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Book> getAllBooksByAuthor() {
        Author author = new Author("George", "Powell");

        return this.bookRepository.findBookByAuthorOrderByReleaseDateDescTitleAsc(author);


    }

    private Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();

        Random random = new Random();

        int bound = random.nextInt(this.categoryService.getAllCategoriesCount()) + 1;

        for (int i = 1; i <= bound; i++) {
            result.add(this.categoryService.getRandomCategoryById((long) i));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {

            sb.append(params[i]).append(" ");

        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }


}
