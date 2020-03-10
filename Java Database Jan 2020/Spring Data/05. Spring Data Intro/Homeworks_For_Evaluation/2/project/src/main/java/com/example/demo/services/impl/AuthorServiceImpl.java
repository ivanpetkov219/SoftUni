package com.example.demo.services.impl;

import com.example.demo.constants.GlobalConstants;
import com.example.demo.entities.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.services.AuthorService;
import com.example.demo.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }
        String[] fileContent = this.fileUtil
                .readFileContent(GlobalConstants.AUTHOR_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = new Author(params[0], params[1]);
                    this.authorRepository.saveAndFlush(author);
                });

    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBook();
    }

    @Override
    public List<Author> findAllAuthors() {

         return this.authorRepository.findAuthorByBooksBefore();

    }
}
