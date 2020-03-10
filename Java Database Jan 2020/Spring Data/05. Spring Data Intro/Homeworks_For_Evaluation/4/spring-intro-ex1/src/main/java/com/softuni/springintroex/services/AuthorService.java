package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    void seedAutors() throws IOException;

    int getAllAuthorsCount();
    Author findAuthorById(Long id);
    List<Author> findAllAuthorsByCountOfBooks();
    Set<Author> findAutorsWithBookWithReleaseDateBefore1990();
}
