package com.softuni.xmlparsingexerciseone.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] readFileContent(String filePath) throws IOException {

        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        Set<String> result = new LinkedHashSet<>();
        String line;

        while((line = reader.readLine()) != null) {
            if(!"".equals(line)) {
                result.add(line);
            }
        }

        return result.toArray(String[]::new);
    }

    @Override
    public void write(String value) throws IOException {
        File file =new File("src/main/resources/resultFiles/result.txt");
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(value);
        myWriter.close();
    }
}
