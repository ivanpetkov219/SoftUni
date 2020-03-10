package com.example.demo.repositories;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book>findAllByReleaseDateAfter(LocalDate localDate);

    //@Query("SELECT a FROM Author AS a")
    //List<Book>findAllByReleaseDateBefore(LocalDate localDate);


    @Query("SELECT b FROM Book AS b WHERE concat(b.author.firstName, ' ', b.author.lastName) = 'George Powell' ORDER BY b.releaseDate DESC, b.title ASC")
    List<Book>findAllByBooksGeorgePowell();


    List<Book>findAllByReleaseDateBefore(LocalDate localDate);



}
