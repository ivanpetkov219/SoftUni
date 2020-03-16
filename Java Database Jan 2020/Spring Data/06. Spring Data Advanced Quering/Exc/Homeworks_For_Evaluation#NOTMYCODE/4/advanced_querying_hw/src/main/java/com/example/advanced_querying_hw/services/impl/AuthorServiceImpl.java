package com.example.advanced_querying_hw.services.impl;

import com.example.advanced_querying_hw.entities.Author;
import com.example.advanced_querying_hw.repositories.AuthorRepository;
import com.example.advanced_querying_hw.services.AuthorService;
import com.example.advanced_querying_hw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.advanced_querying_hw.constants.GlobalConstants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;
    private final BufferedReader rd;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
        this.rd = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void seedAuthors() throws IOException {

        if (this.authorRepository.count() != 0)
            return;

        this.fileUtil.readFileData(AUTHORS_FILE_PATH).forEach(s ->
                this.authorRepository.saveAndFlush(new Author(s.split("\\s+")[0], s.split("\\s+")[1])));
    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> getAuthorsWithFirstNameEndingWith() throws IOException {
        System.out.println("Enter letter/s:");
        String str = rd.readLine();
        List<Author> list = this.authorRepository.findByFirstNameEndingWith(str);
        if (list.isEmpty())
            System.out.println("No authors found with first name ending with " + str);
        return list;
    }

    @Override
    public List<Object> getAuthorsTotalCopies() {
        return this.authorRepository.getAuthorsTotalCopies();
    }


}
