package com.springdataintro.springintroex.Services.ServiceImpl;

import com.springdataintro.springintroex.Services.AuthorService;
import com.springdataintro.springintroex.constants.GlobalConstants;
import com.springdataintro.springintroex.entities.Author;
import com.springdataintro.springintroex.repositories.AuthorRepository;
import com.springdataintro.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;
//    private PreparedStatement statement;
//    private final Connection connection;

    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

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
    public List<String> getAllAuthorsByBookCount() {

        return this.authorRepository
                .findAll()
                .stream()
                .sorted(
                        (a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByNameEnding(String nameEnd) {
        return this.authorRepository.getAllByFirstNameEndingWith(nameEnd);
    }

    @Override
    public List<Author> getAllByBookCopies() {
        return this.authorRepository.getAllByBookCopies();
    }

    //TODO: NOT WORKING
//    @Override
//    public ResultSet getBooksByAuthor(String firstName, String lastName) {
//        try {
//            this.statement = this.connection.prepareStatement("CALL books_by_author(?,?)");
//            this.statement.setString(1, firstName);
//            this.statement.setString(2, lastName);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
}
