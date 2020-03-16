package com.example.advanced_querying_hw.repositories;

import com.example.advanced_querying_hw.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstNameEndingWith(String firstName);

    @Query("select concat(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) " +
            "from Author a " +
            "join a.books b " +
            "group by a.id " +
            "order by sum(b.copies) desc")
    List<Object> getAuthorsTotalCopies();
}
