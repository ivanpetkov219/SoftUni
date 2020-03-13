package com.vankata.advancedquering.repositories;


import com.vankata.advancedquering.entities.AgeRestriction;
import com.vankata.advancedquering.entities.Book;
import com.vankata.advancedquering.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

   List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

   List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

   List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal less, BigDecimal greater);

   List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

   List<Book> findAllByReleaseDateBefore(LocalDate date);

   List<Book> findAllByTitleContaining(String string);

   @Query("SELECT b FROM Book AS b WHERE length(b.title) > :number ")
   List<Book> findAllByTitleLongerThan(@Param("number") int number);

   @Query("SELECT sum(b.copies) FROM Book AS b WHERE concat(b.author.firstName, ' ', b.author.lastName) = :name")
   int findAllBookCopiesByAuthor(@Param("name") String fullName);

   Book findByTitle(String title);

}
