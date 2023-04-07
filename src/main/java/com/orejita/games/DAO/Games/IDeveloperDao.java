package com.orejita.games.DAO.Games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Games.Developer;

@Repository
public interface IDeveloperDao extends JpaRepository<Developer, Integer> {
    
}
