package com.orejita.games.DAO.Rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Rating.Rating;

@Repository
public interface IRatingDao extends JpaRepository<Rating, Long> {

    List<Rating> findAllByConsoleId(Long consoleId);
    List<Rating> findAllByGameId(Long gameId);
    
}
