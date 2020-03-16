package com.example.demo.repositories;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Enums.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b " +
            "WHERE b.ageRestriction = upper(:str)")
    Set<Book> getByAgeRestriction(@Param(value = "str") String str);

    Set<Book> getBooksByEditionLikeAndCopiesLessThan(Edition edition, int copies);

    Set<Book> getBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    @Query("SELECT b FROM Book b " +
            "WHERE substring(b.releaseDate,1,4) <> :year ")
    Set<Book> getBooksByReleaseDateNot(@Param(value = "year") String year);

    Set<Book> getAllByReleaseDateBefore(Date date);

    Set<Book> getAllByTitleContaining(String str);

    @Query("SELECT b FROM Book b " +
            "WHERE b.author.lastName like concat('%',:str,'%') ")
    Set<Book> getAllByAuthorWithLastName(@Param(value = "str") String str);

    @Query("SELECT count(b) FROM Book b " +
            "WHERE length(b.title) > :num ")
    int countAllByTitleGreaterThan(@Param(value = "num") int num);

    @Query("SELECT b FROM Book b " +
            "WHERE b.title like concat('%',:title,'%')")
    Set<Book> getBookByTitle(@Param("title") String title);

    Set<Book> getAllByOrderByCopiesDesc();
}
