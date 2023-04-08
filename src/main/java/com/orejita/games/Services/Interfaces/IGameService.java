package com.orejita.games.Services.Interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orejita.games.Entities.Games.Game;

@Service
public interface IGameService {

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
    void deleteGame(long id);
    
}
