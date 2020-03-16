package com.example.advanced_querying_hw.services;

import com.example.advanced_querying_hw.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    int getAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> getAuthorsWithFirstNameEndingWith() throws IOException;

    List<Object> getAuthorsTotalCopies();
}
