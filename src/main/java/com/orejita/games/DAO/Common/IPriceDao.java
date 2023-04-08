package com.orejita.games.DAO.Common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Common.Price;

@Repository
public interface IPriceDao extends JpaRepository<Price, Long> {

    List<Price> findByConsoleHistoryPrice(Long consoleId);
    List<Price> findByGameHistoryPrice(Long gameId);
    Price findFirstByConsoleHistoryPriceOrderById(Long consoleId);
    Price findFirstByGameHistoryPriceOrderById(Long gameId);
    
}
