package com.example.demo.services;

import com.example.demo.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book>getAllBooksAfter2000();

    List<Book>findAllByBooksGeorgePowell();

    List<Book>findAllBookReleasedDateBefore1990();



}
