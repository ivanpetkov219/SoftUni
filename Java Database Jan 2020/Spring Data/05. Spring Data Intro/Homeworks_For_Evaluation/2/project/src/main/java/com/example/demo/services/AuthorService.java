package com.example.demo.services;

import com.example.demo.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author>findAllAuthorsByCountOfBooks();

    List<Author>findAllAuthors();
}
