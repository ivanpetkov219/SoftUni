package com.example.sprintdataintroex.repositories;

import com.example.sprintdataintroex.entities.Author;
import com.example.sprintdataintroex.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findBookByAuthorOrderByReleaseDateDescTitleAsc(Author author);

}
