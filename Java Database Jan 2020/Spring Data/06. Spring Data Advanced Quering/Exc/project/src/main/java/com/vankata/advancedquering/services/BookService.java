package com.vankata.advancedquering.services;



import com.vankata.advancedquering.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getBooksByAgeRestriction(String ageRestriction);

    List<Book> getAllBooksOfEditionHavingLessThanCopies(String editionType, int copies);

    List<Book> getAllBooksWithPriceLowerThanAndHigherThan(int lowerThan, int higherThan);

    List<Book> getAllBooksBeforeOrAfter(int year);

    List<Book> getAllBooksReleasedBefore(String date);

    List<Book> getAllBooksWithTitleContaining(String string);

    List<Book> getAllBooksWithTitleLongerThan(int number);

    int getAllCopiesByAuthor(String fullName);

    Book findBookDetailsByGivenTitle(String title);
}
