package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;
    private BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAutors() throws IOException {
        if (this.authorRepository.count()!=0){
            return;
        }
        String [] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHOR_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r ->{
                    String [] param = r.split("\\s+");
                    Author author = new Author(param[0], param[1]);
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
        return this.authorRepository.findAuthorByCountofBook();
    }

    @Override
    public Set<Author> findAutorsWithBookWithReleaseDateBefore1990() {
        List<Book> books = this.bookService.findAllByReleaseDateIsBefore1990();

    Set<Author> authors = new LinkedHashSet<>();
        for (Book book : books) {
        authors.add(book.getAuthor());
    }

        return authors;
    }
}
