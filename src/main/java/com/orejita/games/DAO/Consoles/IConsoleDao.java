package com.orejita.games.DAO.Consoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Consoles.Console;

@Repository
public interface IConsoleDao extends JpaRepository<Console, Integer> {
    
}
