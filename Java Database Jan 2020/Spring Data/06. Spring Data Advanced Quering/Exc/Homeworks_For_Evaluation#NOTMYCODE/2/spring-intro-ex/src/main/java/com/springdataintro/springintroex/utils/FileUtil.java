package com.springdataintro.springintroex.utils;

import java.io.IOException;

public interface FileUtil {
    String[] readFileContent(String filePath) throws IOException;
}
