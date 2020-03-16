package com.example.advanced_querying_hw.services;

import com.example.advanced_querying_hw.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getBookTitlesByAgeRestriction() throws IOException;

    List<String> getGoldenBooksWithLessThan5000Copies();

    List<Book> getBooksWithPriceLessThan5OrMoreThan40();

    List<String> getBooksNotReleasedInYear() throws IOException;

    List<Book> getBooksReleasedBeforeDate() throws IOException;

    List<String> getBooksThatContainString() throws IOException;

    List<Book> getBooksWithAuthorLastNameStartingWith() throws IOException;

    void countBooksWithTitleLongerThan() throws IOException;

    String getBookInfoByTitle() throws IOException;

    void increaseBookCopiesAfterDateBy() throws IOException;

    void removeBooksWithCopiesLessThan() throws IOException;
}
