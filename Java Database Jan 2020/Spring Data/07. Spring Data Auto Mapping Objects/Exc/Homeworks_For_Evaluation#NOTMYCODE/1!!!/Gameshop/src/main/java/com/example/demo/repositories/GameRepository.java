package com.example.demo.repositories;

import com.example.demo.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameById(long id);
    Game findGameByTitle(String title);
    @Query("SELECT g FROM Game AS g")
    List<Game> getAllGames();

    @Modifying
    @Query("UPDATE Game AS g SET g.title = :value WHERE g.id = :id")
    int editTitleById( @Param("value") String title, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.description = :value WHERE g.id = :id")
    int editDescriptionById( @Param("value") String description, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.image = :value WHERE g.id = :id")
    int editImageById( @Param("value") String image, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.price = :value WHERE g.id = :id")
    int editPriceById( @Param("value") BigDecimal price, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.size = :value WHERE g.id = :id")
    int editSizeById( @Param("value") double size, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.trailer = :value WHERE g.id = :id")
    int editTrailerById( @Param("value") String trailer, @Param("id") long id);

    @Modifying
    @Query("UPDATE Game AS g SET g.releaseDate = :value WHERE g.id = :id")
    int editReleaseDateById( @Param("value") LocalDate releaseDate, @Param("id") long id);
}
