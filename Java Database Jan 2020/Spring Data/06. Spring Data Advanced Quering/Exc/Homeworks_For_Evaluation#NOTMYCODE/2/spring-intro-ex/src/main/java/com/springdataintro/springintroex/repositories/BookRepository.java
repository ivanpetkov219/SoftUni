package com.springdataintro.springintroex.repositories;

import com.springdataintro.springintroex.entities.AgeRestriction;
import com.springdataintro.springintroex.entities.Author;
import com.springdataintro.springintroex.entities.Book;
import com.springdataintro.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByTitleContaining(String partOfTitle);

    @Query("SELECT b FROM Book b WHERE b.author.lastName LIKE concat(:part,'%')")
    List<Book> findAllByAuthorLastNameStartsWith(@Param("part") String startsWith);

    @Query("SELECT count(b) FROM Book b WHERE length(b.title) > :param")
    int findAllByTitleThatIsLongerThan(@Param("param") int size);

    List<Book> findAllByTitle(String title);

    @Modifying
    @Query("update Book AS b set b.copies = b.copies + :copies where b.releaseDate > :date")
    int updateBookCopies(@Param("copies") int copies, @Param("date") LocalDate date);

    @Modifying
    @Query("delete from Book b where b.copies < :copies")
    int removeBooksThatCopiesAreLessThanGivenNumber(@Param("copies") int copies);
}
