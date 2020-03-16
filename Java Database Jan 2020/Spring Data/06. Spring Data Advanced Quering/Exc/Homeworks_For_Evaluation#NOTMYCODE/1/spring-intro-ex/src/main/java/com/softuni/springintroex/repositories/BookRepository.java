package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.enums.AgeRestriction;
import com.softuni.springintroex.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findBookByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByAuthorOrderByReleaseDateDesc(Author author);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal greater);

    List<Book> findAllByReleaseDateBeforeAndReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByTitleContains(String pattern);

    @Query("SELECT b FROM Book AS b WHERE b.author.lastName LIKE :pattern")
    List<Book> findAllBooksWhereAuthorLastNameContainsGivenPattern(@Param("pattern") String pattern);

    @Query("SELECT b FROM Book AS b WHERE length(b.title) > :lengthInput")
    List<Book> findCountOfBooksByGivenCharLength(@Param("lengthInput") int length);

    @Query("SELECT b FROM Book AS b WHERE concat(b.author.firstName, ' ', b.author.lastName) = :fullName")
    List<Book> findAllBooksByGivenAuthor(@Param("fullName") String fullName);

    Book findFirstByTitle(String title);
}
