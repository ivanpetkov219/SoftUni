package com.example.advanced_querying_hw.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil{

    @Override
    public List<String> readFileData(String p) throws IOException {

        return Files.readAllLines(Paths.get(p)).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
}
