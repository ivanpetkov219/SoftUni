package com.springdataintro.springintroex.Services;

import com.springdataintro.springintroex.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllTitles();

    List<String> findAllAuthors();

    List<String> findGeorgePowellBooks();

    List<Book> getAllBooksByAgeRestriction(String ageRestriction);

    List<Book> getAllBooksByEditionTypeAndCopies(String editionType, int copies);

    List<Book> getAllBooksByReleaseDateNotInYear(int year);

    List<Book> getAllBooksByPriceLowerThanOrHigherThanCriteria(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> getAllBooksReleasedBefore(String date);

    List<Book> findAllByTitleContaining(String titlePart);

    List<Book> findAllByAuthorLastNameStartsWith(String part);

    int findAllByTitleThatIsLongerThan(int size);

    List<String> findBookByTitle(String title);

    int updateBookCopies(int copies, String date);

    int removeBooksThatCopiesAreLessThanGivenNumber(int copies);
}
