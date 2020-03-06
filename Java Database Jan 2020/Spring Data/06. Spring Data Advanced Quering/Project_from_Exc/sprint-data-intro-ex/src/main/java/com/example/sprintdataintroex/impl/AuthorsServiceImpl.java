package com.example.sprintdataintroex.impl;

import com.example.sprintdataintroex.constants.GlobalConstants;
import com.example.sprintdataintroex.entities.Author;
import com.example.sprintdataintroex.entities.Category;
import com.example.sprintdataintroex.repositories.AuthorRepository;
import com.example.sprintdataintroex.services.AuthorService;
import com.example.sprintdataintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
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
        if(this.authorRepository.count() != 0){ //this is the easy way
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
}
