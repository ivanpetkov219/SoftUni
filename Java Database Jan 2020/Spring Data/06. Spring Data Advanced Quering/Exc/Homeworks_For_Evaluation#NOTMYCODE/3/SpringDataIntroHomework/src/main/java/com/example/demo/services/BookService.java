package com.example.demo.services;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public interface BookService {
    void save(Book book);

}
