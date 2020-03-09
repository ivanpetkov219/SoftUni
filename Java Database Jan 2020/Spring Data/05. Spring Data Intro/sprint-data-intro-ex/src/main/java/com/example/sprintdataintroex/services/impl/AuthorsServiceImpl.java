package com.example.sprintdataintroex.services.impl;

import com.example.sprintdataintroex.constants.GlobalConstants;
import com.example.sprintdataintroex.entities.Author;
import com.example.sprintdataintroex.repositories.AuthorRepository;
import com.example.sprintdataintroex.services.AuthorService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AuthorsServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final FileUtil fileUtil;

    @Autowired
    public AuthorsServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHOR_FILE_PATH);

        Arrays.stream(fileContent).forEach(a ->
        {
            String[] params = a.split("\\s+");
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
    public List<Author> getAllAuthorsWithBookBefore1990() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("01/01/1990", formatter);

        List<Author> authors = this.authorRepository.findAuthorsByBooksBefore(releaseDate);
        return authors;
    }

    @Override
    public List<Author> getAllAuthorsByCountOfBooksDesc() {

        return  this.authorRepository.findAuthorsByCountOfBooks();
    }
}
