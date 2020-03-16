package com.example.demo.services;

import com.example.demo.models.Author;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public interface AuthorService {
    void save(Author author);

}
