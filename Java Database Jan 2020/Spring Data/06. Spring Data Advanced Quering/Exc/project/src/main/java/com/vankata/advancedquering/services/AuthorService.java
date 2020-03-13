package com.vankata.advancedquering.services;

import com.vankata.advancedquering.entities.Author;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);

    List<Author> getAllAuthorsWithFirstNameEndingWith(String end);

    List<Author> getAllAuthorsByFirstNameStartingWith(String string);


}
