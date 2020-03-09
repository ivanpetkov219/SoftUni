package com.example.sprintdataintroex.services;

import com.example.sprintdataintroex.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    List<Book> getAllBooksReleasedAfterYear2000();
    List<Book> getAllBooksByAuthor();
}
