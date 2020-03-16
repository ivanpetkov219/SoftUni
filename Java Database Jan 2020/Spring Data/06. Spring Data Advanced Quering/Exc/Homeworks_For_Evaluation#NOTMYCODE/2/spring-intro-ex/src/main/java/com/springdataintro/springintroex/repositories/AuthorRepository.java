package com.springdataintro.springintroex.repositories;

import com.springdataintro.springintroex.entities.Author;
import com.springdataintro.springintroex.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAllByFirstNameEndingWith(String nameEnd);

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> getAllByBookCopies();

//    @Query(value = "CALL books_by_author(':first_name', ':last_name');", nativeQuery = true)
//    Author getBooksByAuthor(@Param("first_name") String firstName, @Param("last_name") String lastName);
}
