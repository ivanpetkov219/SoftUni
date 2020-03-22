package com.example.spingautomappingex.domain.repositories;

import com.example.spingautomappingex.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findById(long id);

    Game findByTitle(String title);

}
