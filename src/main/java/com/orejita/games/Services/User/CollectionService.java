package com.orejita.games.Services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.DAO.User.ICollectionDao;
import com.orejita.games.DAO.User.IUserDao;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.User.Collection;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Exceptions.Console.ConsoleNotFoundException;
import com.orejita.games.Exceptions.Game.GameNotFoundException;
import com.orejita.games.Services.Interfaces.ICollectionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CollectionService implements ICollectionService {
    
    @Autowired
    private ICollectionDao dao;

    @Autowired
    private IGameDao gameDao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public List<Collection> getAllCollections() {
        return dao.findAll();
    }

    @Override
    public Collection getOneCollection(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Collection getCollectionByUserId(Long userId) {
        return dao.findByUserId(userId).orElse(null);
    }

    @Override
    public Collection getCollectionByUserUsername(String username) {
        return dao.findByUserUsername(username).orElse(null);
    }

    @Override
    public Collection createCollection(long userId, Collection collection) {
        User user = userDao.findById(userId).orElse(null);
        collection.setUser(user);
        return dao.save(collection);
    }

    @Override
    public Collection updateCollection(Long id, Collection collection) {
        Collection _collection = this.getOneCollection(id);

        if (_collection == null) {
            return null;
        }

        return collection;
    }

    @Override
    public Collection addGameToCollection(Long collectionId, Long gameId) {
        Collection collection = this.getOneCollection(collectionId);
        List<Game> games = collection.getGames();
        Game game = gameDao.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found, id " + gameId));
        games.add(game);
        collection.setGames(games);
        return dao.save(collection);
    }

    @Override
    public Collection addGameToCollectionByUser(Long userId, Long gameId) {
        Collection collection = this.getCollectionByUserId(userId);
        List<Game> games = collection.getGames();
        Game game = gameDao.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found, id " + gameId));
        games.add(game);
        collection.setGames(games);
        return dao.save(collection);
    }

    @Override
    public Collection addConsoleToCollection(Long collectionId, Long consoleId) {
        Collection collection = this.getOneCollection(collectionId);
        List<Console> consoles = collection.getConsoles();
        Console console = consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found, id " + consoleId));
        consoles.add(console);
        collection.setConsoles(consoles);
        return dao.save(collection);
    }

    @Override
    public Collection addConsoleToCollectionByUser(Long userId, Long consoleId) {
        Collection collection = this.getCollectionByUserId(userId);
        List<Console> consoles = collection.getConsoles();
        Console console = consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found, id " + consoleId));
        consoles.add(console);
        collection.setConsoles(consoles);
        return dao.save(collection);
    }

    @Override
    public Collection deleteGameFromCollection(Long collectionId, Long gameId) {
        Collection collection = this.getOneCollection(collectionId);
        List<Game> games = collection.getGames();
        games.stream().filter(game -> game.getId() != gameId).toList();
        collection.setGames(games);
        return dao.save(collection);
    }

    @Override
    public Collection deleteGameFromCollectionByUser(Long userId, Long gameId) {
        Collection collection = this.getCollectionByUserId(userId);
        List<Game> games = collection.getGames();
        games.stream().filter(game -> game.getId() != gameId).toList();
        collection.setGames(games);
        return dao.save(collection);
    }

    @Override
    public Collection deleteConsoleFromCollection(Long collectionId, Long consoleId) {
        Collection collection = this.getOneCollection(collectionId);
        List<Console> consoles = collection.getConsoles();
        consoles.stream().filter(console -> console.getId() != consoleId).toList();
        collection.setConsoles(consoles);
        return dao.save(collection);
    }

    @Override
    public Collection deleteConsoleFromCollectionByUser(Long userId, Long consoleId) {
        Collection collection = this.getCollectionByUserId(userId);
        List<Console> consoles = collection.getConsoles();
        consoles.stream().filter(console -> console.getId() != consoleId).toList();
        collection.setConsoles(consoles);
        return dao.save(collection);
    }

    @Override
    public void deleteCollection(Long id) {
        dao.deleteById(id);
    }



}
