package com.orejita.games.Services.Interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.IService;

@Service
public interface IGameService extends IService<Game> {

    List<Game> getAllGames();
    List<Game> getAllGamesByConsole(long consoleId);
    List<Game> getAllGamesByManufacturer(long manufacturerId);
    Game getOneGame(long gameId);
    Game getGameByName(String name);
    Game getGameBySlug(String slug);
    Game createGame(long consoleId, Game game);
    Game updateGame(long id, Game game);
    Game setGameImages(long gameId, List<String> images);
    Game setGameImages(Game game, List<String> images);
    Game setGameBoxImages(long gameId, List<String> images);
    Game setGameBoxImages(Game game, List<String> images);
    Game deleteGameImage(long gameId, String image);
    Game deleteGameBoxImage(long gameId, String image);
    Game addTagToGame(long gameId, long tagId);
    Game deleteTagFromGame(long gameId, long tagId);
    void deleteGame(long id);
    
}
