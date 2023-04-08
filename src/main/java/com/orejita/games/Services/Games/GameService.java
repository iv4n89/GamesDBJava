package com.orejita.games.Services.Games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IGameService;

@Service
public class GameService implements IGameService {

    @Autowired
    private IGameDao dao;

    @Autowired IConsoleDao consoleDao;

    @Override
    public List<Game> getAllGames() {
        return dao.findAll();
    }

    @Override
    public List<Game> getAllGamesByConsole(long consoleId) {
        return dao.findByConsole(consoleId);
    }

    @Override
    public List<Game> getAllGamesByManufacturer(long manufacturerId) {
        return dao.findByManufacturer(manufacturerId);
    }

    @Override
    public Game getOneGame(long gameId) {
        return dao.findById(gameId).orElse(null);
    }

    @Override
    public Game getGameByName(String name) {
        return dao.findByName(name).orElse(null);
    }

    @Override
    public Game getGameBySlug(String slug) {
        return dao.findBySlug(slug).orElse(null);
    }

    @Override
    public Game createGame(long consoleId, Game game) {
        Console console = consoleDao.findById(consoleId).orElse(null);

        if (console == null) {
            return null;
        }

        return dao.save(game);
    }

    @Override
    public Game updateGame(long id, Game game) {
        Game _game = this.getOneGame(id);

        if (_game == null) {
            return null;
        }

        if (game.getName() != null) {
            _game.setName(game.getName());
        }
        if (game.getSlug() != null) {
            _game.setSlug(game.getSlug());
        }
        if (game.getDescription() != null) {
            _game.setDescription(game.getDescription());
        }
        
        return dao.save(_game);
    }

    @Override
    public Game setGameImages(long gameId, List<String> images) {
        Game game = this.getOneGame(gameId);
        game.setImages(images);
        return dao.save(game);
    }

    @Override
    public Game setGameImages(Game game, List<String> images) {
        game.setImages(images);
        return dao.save(game);
    }

    @Override
    public Game setGameBoxImages(long gameId, List<String> images) {
        Game game = this.getOneGame(gameId);
        game.setBoxImages(images);
        return dao.save(game);
    }

    @Override
    public Game setGameBoxImages(Game game, List<String> images) {
        game.setBoxImages(images);
        return dao.save(game);
    }

    @Override
    public Game deleteGameImage(long gameId, String image) {
        Game game = this.getOneGame(gameId);
        List<String> images = game.getImages().stream().filter(img -> !img.contains(image)).toList();
        game.setImages(images);
        return dao.save(game);
    }

    @Override
    public Game deleteGameBoxImage(long gameId, String image) {
        Game game = this.getOneGame(gameId);
        List<String> images = game.getBoxImages().stream().filter(img -> !img.contains(image)).toList();
        game.setBoxImages(images);
        return dao.save(game);
    }

    @Override
    public void deleteGame(long id) {
        dao.deleteById(id);
    }
    
}
