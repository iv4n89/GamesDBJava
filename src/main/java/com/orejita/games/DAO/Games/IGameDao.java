package com.orejita.games.DAO.Games;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Games.Game;

@Repository
public interface IGameDao extends JpaRepository<Game, Long> {

    List<Game> findByConsole(Long consoleId);
    List<Game> findByManufacturer(Long manufacturerId);
    Optional<Game> findByName(String name);
    Optional<Game> findBySlug(String slug);
    
}
