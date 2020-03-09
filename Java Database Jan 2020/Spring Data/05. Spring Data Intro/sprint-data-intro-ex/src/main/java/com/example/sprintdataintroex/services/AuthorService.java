package com.example.sprintdataintroex.services;


import com.example.sprintdataintroex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);
    List<Author> getAllAuthorsWithBookBefore1990();
    List<Author> getAllAuthorsByCountOfBooksDesc();
}
