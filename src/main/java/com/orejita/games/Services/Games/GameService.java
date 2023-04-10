package com.orejita.games.Services.Games;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.IIConDao;
import com.orejita.games.DAO.Common.ITagDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Common.Tag;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Exceptions.Tag.TagNotFoundException;
import com.orejita.games.Services.Interfaces.IGameService;

@Service
public class GameService implements IGameService {

    @Autowired
    private IGameDao dao;

    @Autowired 
    private IConsoleDao consoleDao;

    @Autowired
    private IIConDao iconDao;

    @Autowired
    private ITagDao tagDao;

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

        game.setConsole(console);
        if (game.getIsSpecialEdition() == null) {
            game.setIsSpecialEdition(0);
        }
        if (game.getIcon() == null && game.getLogo() != null) {
            Icon icon = new Icon();
            icon.setUrl(game.getLogo());
            Icon _icon = iconDao.save(icon);
            game.setIcon(_icon);
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
        if (game.getIsSpecialEdition() != null) {
            _game.setIsSpecialEdition(game.getIsSpecialEdition());
        }
        if (game.getZone() != null) {
            _game.setZone(game.getZone());
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
    public Game addTagToGame(long gameId, long tagId) {
        Game game = this.getOneGame(gameId);
        Tag tag = this.tagDao.findById(tagId).orElseThrow(() -> new TagNotFoundException("Tag not found"));
        List<Tag> gameTags = game.getTags();
        gameTags.add(tag);
        List<Tag> _gameTags = gameTags.stream().collect(Collectors.toSet()).stream().toList();
        game.setTags(_gameTags);
        return this.dao.save(game);
    }

    @Override
    public Game deleteTagFromGame(long gameId, long tagId) {
        Game game = this.getOneGame(gameId);
        List<Tag> tags = game.getTags();
        List<Tag> gameTags = tags.stream().filter(tag -> tag.getId() != tagId).toList();
        game.setTags(gameTags);
        return dao.save(game);
    }

    @Override
    public void deleteGame(long id) {
        dao.deleteById(id);
    }
    
}
