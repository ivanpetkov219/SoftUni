package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.enums.AgeRestriction;
import com.softuni.springintroex.enums.EditionType;
import com.sun.source.tree.LambdaExpressionTree;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();

    List<Book> getAllBooksBefore1990();

    List<Book> getAllBooksFromAuthor();

    List<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getAllBooksByEditionTypeAndLessThan5000Copies(EditionType editionType, int copies);

    List<Book> getAllBooksByPriceLowerAndHigherThanGiven(BigDecimal lower, BigDecimal greater);

    List<Book> getAllBooksThatAreNotInGivenInput(LocalDate before, LocalDate after);

    List<Book> getAllBooksByReleaseDateBeforeGiven(LocalDate before);

    List<Book> getAllBooksThatContainsGivenPattern(String pattern);

    List<Book> getAllBooksByAuthorLastNameContainsGivenStringPattern(String pattern);

    List<Book> getAllBooksByGivenCharacterLength(int length);

    List<Book> getBooksByGivenAuthorName(String name);

    Book getBookByGivenTitle(String title);
}
