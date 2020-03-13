package com.vankata.advancedquering.repositories;


import com.vankata.advancedquering.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

   List<Author> findAllByFirstNameEndingWith(String end);

   List<Author> findAllByLastNameStartingWith(String string);

   List<Author> findAllByBooksNotNullOrderByBooksDesc();




}
