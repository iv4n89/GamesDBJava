package com.orejita.games.Services.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.ITagDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.Entities.Common.Tag;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Exceptions.Console.ConsoleNotFoundException;
import com.orejita.games.Exceptions.Game.GameNotFoundException;
import com.orejita.games.Services.Interfaces.ITagService;

@Service
public class TagService implements ITagService {

    @Autowired
    private ITagDao dao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IGameDao gameDao;

    @Override
    public List<Tag> getAllTags() {
        return dao.findAll();
    }

    @Override
    public List<Tag> getAllConsoleTags(long consoleId) {
        return dao.findAllByConsoleId(consoleId).orElse(null);
    }

    @Override
    public List<Tag> getAllGameTags(long gameId) {
        return dao.findAllByGameId(gameId).orElse(null);
    }

    @Override
    public Tag getOneTag(long id) {
        return dao.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public Tag createTag(Tag tag) {
        return dao.save(tag);
    }

    @Override
    public Tag updateTag(long id, Tag tag) {
        Tag _tag = this.getOneTag(id);

        if (tag.getName() != null) {
            _tag.setName(tag.getName());
        }
        if (tag.getColor() != null) {
            _tag.setColor(tag.getColor());
        }

        return dao.save(_tag);
    }

    @Override
    public Tag addConsoleToTag(long id, long consoleId) {
        Tag tag = this.getOneTag(id);
        Console console = consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found"));
        List<Console> consoles = tag.getConsoles();
        consoles.add(console);
        List<Console> tagConsoles = consoles.stream().collect(Collectors.toSet()).stream().toList();
        tag.setConsoles(tagConsoles);
        return dao.save(tag);
    }

    @Override
    public Tag addConsolesToTag(long id, Set<Long> consoleIds) {
        Tag tag = this.getOneTag(id);
        List<Console> consoles = new ArrayList<>();
        consoleIds.stream().forEach(consoleId -> {
            Console console = consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found"));
            consoles.add(console);
        });
        List<Console> tagConsoles = tag.getConsoles();
        tagConsoles.addAll(consoles);
        List<Console> _tagConsoles = tagConsoles.stream().collect(Collectors.toSet()).stream().toList();
        tag.setConsoles(_tagConsoles);
        return dao.save(tag);
    }

    @Override
    public Tag addGameToTag(long id, long gameId) {
        Tag tag = this.getOneTag(id);
        List<Game> games = tag.getGames();
        Game game = gameDao.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found"));
        games.add(game);
        List<Game> tagGames = games.stream().collect(Collectors.toSet()).stream().toList();
        tag.setGames(tagGames);
        return dao.save(tag);
    }

    @Override
    public Tag addGamesToTag(long id, Set<Long> gamesId) {
        Tag tag = this.getOneTag(id);
        List<Game> games = new ArrayList<>();
        gamesId.stream().forEach(gameId -> {
            Game game = gameDao.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found"));
            games.add(game);
        });
        List<Game> tagGames = tag.getGames();
        tagGames.addAll(games);
        List<Game> _tagGames = tagGames.stream().collect(Collectors.toSet()).stream().toList();
        tag.setGames(_tagGames);
        return dao.save(tag);
    }

    @Override
    public void deleteTag(long id) {
        dao.deleteById(id);
    }


    
}
