package com.example.advanced_querying_hw.repositories;

import com.example.advanced_querying_hw.entities.AgeRestriction;
import com.example.advanced_querying_hw.entities.Book;
import com.example.advanced_querying_hw.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal moreThan);

    @Query("select b from Book b where function('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNotYear(@Param("year") int year);

    List<Book> getByReleaseDateBefore(LocalDate releaseDate);

    @Query("select b from Book b where lower(b.title) like :str")
    List<Book> getByTitleContaining(@Param("str") String str);

//    Today I learned "Containing" is case insensitive...
//    List<Book> getAllByByTitleContaining(String str);

    @Query("select b from Book b where b.author.lastName like :str")
    List<Book> getByAuthorLastNameStartingWith(@Param("str") String str);

    @Query("select count(b) from Book b where length(b.title) > :num")
    int countByTitleLongerThan(@Param("num") int num);

    Book getBookByTitle(String title);

    @Modifying
    @Query("update Book b set b.copies = b.copies + :copies where b.releaseDate > :date")
    int updateBooksAfterDateByNumber(@Param("date") LocalDate date,@Param("copies") int copies);

    @Modifying
    @Query("delete from Book b where b.copies < :copies")
    int removeBooksWithCopiesLessThan(@Param("copies") int copies);
}
