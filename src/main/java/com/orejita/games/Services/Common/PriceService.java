package com.orejita.games.Services.Common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.IPriceDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IPriceService;

@Service
public class PriceService implements IPriceService {

    @Autowired
    private IPriceDao dao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IGameDao gameDao;

    @Override
    public List<Price> getAllPricesByConsole(int consoleId) {
        return dao.findByConsoleHistoryPrice(consoleId);
    }

    @Override
    public List<Price> getAllPricesByGame(int gameId) {
        return dao.findByGameHistoryPrice(gameId);
    }

    @Override
    public Price getOnePrice(int price) {
        return dao.findById(price).orElse(null);
    }

    @Override
    public Price getLastConsolePrice(int consoleId) {
        return dao.findFirstByConsoleHistoryPriceOrderById(consoleId);
    }

    @Override
    public Price getLastGamePrice(int gameId) {
        return dao.findFirstByGameHistoryPriceOrderById(gameId);
    }

    @Override
    public Price createPriceForConsole(Price price, int consoleId) {
        Console console = consoleDao.findById(consoleId).orElse(null);

        if (console == null) {
            return null;
        }

        price.setConsoleHistoryPrice(console);
        return dao.save(price);

    }

    @Override
    public Price createPriceForGame(Price price, int gameId) {
        Game game = gameDao.findById(gameId).orElse(null);

        if (game == null) {
            return null;
        }

        price.setGameHistoryPrice(game);
        return dao.save(price);
    }

    @Override
    public Price updatePrice(int priceId, Price price) {
        Price _price = this.getOnePrice(priceId);

        if (_price == null) {
            return null;
        }

        if (price.getPrice() != null) {
            _price.setPrice(price.getPrice());
        }
        if (price.getPriceDate() != null) {
            _price.setPriceDate(price.getPriceDate());
        }

        return dao.save(_price);
    }

    @Override
    public void deletePrice(int price) {
        dao.deleteById(price);
    }

    
    
}
