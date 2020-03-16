package com.example.advanced_querying_hw.utils;

import java.io.IOException;
import java.util.List;

public interface FileUtil {

    List<String> readFileData(String filePath) throws IOException;
}
