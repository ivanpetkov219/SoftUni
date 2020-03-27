package com.softuni.xmlparsingexerciseone.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {

    String[] readFileContent(String filePath) throws IOException;

    void write(String value) throws IOException;
}
