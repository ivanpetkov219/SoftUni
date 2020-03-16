package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.softuni.springintroex.constants.GlobalConstants.AUTHOR_FILE_PATH;

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

        String[] fileContent = this.fileUtil.readFileContent(AUTHOR_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(row -> {
                    String[] parameters = row.split("\\s+");
                    Author author = new Author(parameters[0], parameters[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository
                .getOne(id);
    }

    @Override
    public List<Author> findAuthorsOrderedByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBook();
    }

    @Override
    public Author findAuthorByName() {
        return this.authorRepository
                .findAuthorByFirstNameAndLastName("George", "Powell");
    }

    @Override
    public List<Author> findAllAuthorsWhereFirstNameEndsWith(String pattern) {
        return this.authorRepository.findAllByFirstNameEndsWith(pattern);
    }
}
