package com.orejita.games.Services.Common;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.IZoneDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.Entities.Common.Zone;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Exceptions.Console.ConsoleNotFoundException;
import com.orejita.games.Exceptions.Game.GameNotFoundException;
import com.orejita.games.Services.Interfaces.IZoneService;

@Service
public class ZoneService implements IZoneService {

    @Autowired
    private IZoneDao dao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IGameDao gameDao;

    @Override
    public List<Zone> getAllZones() {
        return dao.findAll();
    }

    @Override
    public Zone getOneZone(long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Zone createZone(Zone zone) {
        return dao.save(zone);
    }

    @Override
    public Zone updateZone(long id, Zone zone) {
        Zone _zone = this.getOneZone(id);

        if (_zone == null) {
            return null;
        }

        if (zone.getName() != null) {
            _zone.setName(zone.getName());
        }

        return dao.save(_zone);
    }

    @Override
    public Zone addConsoleToZone(long id, long consoleId) {
        Zone zone = this.getOneZone(id);
        Console console = this.consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found"));
        List<Console> consoles = zone.getConsoles();
        consoles.add(console);
        List<Console> zoneConsoles = consoles.stream().collect(Collectors.toSet()).stream().toList();
        zone.setConsoles(zoneConsoles);
        return this.dao.save(zone);
    }

    @Override
    public Zone addConsolesToZone(long id, Set<Long> consolesId) {
        Zone zone = this.getOneZone(id);
        List<Console> consoles = zone.getConsoles();
        List<Console> _consoles = consolesId.stream()
                                    .map(consoleDao::findById)
                                    .map(optional -> optional.orElseThrow(() -> new ConsoleNotFoundException("Console not found")))
                                    .toList();
        consoles.addAll(_consoles);
        List<Console> zoneConsoles = consoles.stream().collect(Collectors.toSet()).stream().toList();
        zone.setConsoles(zoneConsoles);
        return this.dao.save(zone);
    }

    @Override
    public Zone deleteConsoleFromZone(long id, long consoleId) {
        Zone zone = this.getOneZone(id);
        List<Console> consoles = zone.getConsoles().stream().filter(console -> console.getId() != consoleId).toList();
        zone.setConsoles(consoles);
        return this.dao.save(zone);
    }

    @Override
    public Zone addGameToZone(long id, long gameId) {
        Zone zone = this.getOneZone(id);
        Game game = this.gameDao.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found"));
        List<Game> games = zone.getGames();
        games.add(game);
        List<Game> _games = games.stream().collect(Collectors.toSet()).stream().toList();
        zone.setGames(_games);
        return this.dao.save(zone);
    }

    @Override
    public Zone addGamesToZone(long id, Set<Long> gamesId) {
        Zone zone = this.getOneZone(id);
        List<Game> games = zone.getGames();
        List<Game> _games = gamesId.stream()
                                .map(gameDao::findById)
                                .map(optional -> optional.orElseThrow(() -> new GameNotFoundException("Game not found")))
                                .toList();
        games.addAll(_games);
        List<Game> zoneGames = _games.stream().collect(Collectors.toSet()).stream().toList();
        zone.setGames(zoneGames);
        return this.dao.save(zone);
    }

    @Override
    public Zone deleteGameFromZone(long id, long gameId) {
        Zone zone = this.getOneZone(id);
        List<Game> games = zone.getGames().stream().filter(game -> game.getId() != gameId).toList();
        zone.setGames(games);
        return this.dao.save(zone);
    }

    @Override
    public void deleteZone(long id) {
        dao.deleteById(id);
    }


    
}
