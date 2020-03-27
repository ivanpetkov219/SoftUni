package com.softuni.xmlprocessingexercise2.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) throws Exception {
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDateTime.parse(v).toLocalDate();
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}