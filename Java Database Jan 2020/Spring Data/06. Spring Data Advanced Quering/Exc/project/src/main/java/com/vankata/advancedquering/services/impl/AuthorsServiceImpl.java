package com.vankata.advancedquering.services.impl;


import com.vankata.advancedquering.constants.GlobalConstants;
import com.vankata.advancedquering.entities.Author;
import com.vankata.advancedquering.repositories.AuthorRepository;
import com.vankata.advancedquering.services.AuthorService;
import com.vankata.advancedquering.utils.FileUtil;
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
    public List<Author> getAllAuthorsWithFirstNameEndingWith(String end) {
        return this.authorRepository.findAllByFirstNameEndingWith(end);
    }

    @Override
    public List<Author> getAllAuthorsByFirstNameStartingWith(String string) {
        return this.authorRepository.findAllByLastNameStartingWith(string);
    }


}
