package com.orejita.games.DAO.Common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Common.Price;

@Repository
public interface IPriceDao extends JpaRepository<Price, Integer> {

    List<Price> findByConsoleHistoryPrice(Integer consoleId);
    List<Price> findByGameHistoryPrice(Integer gameId);
    Price findFirstByConsoleHistoryPriceOrderById(Integer consoleId);
    Price findFirstByGameHistoryPriceOrderById(Integer gameId);
    
}
