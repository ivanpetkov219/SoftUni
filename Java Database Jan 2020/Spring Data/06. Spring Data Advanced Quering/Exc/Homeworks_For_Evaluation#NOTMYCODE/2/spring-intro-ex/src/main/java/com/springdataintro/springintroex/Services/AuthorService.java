package com.springdataintro.springintroex.Services;

import com.springdataintro.springintroex.entities.Author;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAllAuthorsCount();

    List<String> getAllAuthorsByBookCount();

    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByNameEnding(String nameEnd);

    List<Author> getAllByBookCopies();

//    ResultSet getBooksByAuthor(String firstName, String lastName);
}
