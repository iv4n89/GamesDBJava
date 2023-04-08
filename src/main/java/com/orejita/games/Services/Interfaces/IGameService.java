package com.orejita.games.Services.Interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orejita.games.Entities.Games.Game;

@Service
public interface IGameService {

    List<Game> getAllGames();
    List<Game> getAllGamesByConsole(int consoleId);
    List<Game> getAllGamesByManufacturer(int manufacturerId);
    Game getOneGame(int gameId);
    Game getGameByName(String name);
    Game getGameBySlug(String slug);
    Game createGame(int consoleId, Game game);
    Game updateGame(int id, Game game);
    Game setGameImages(int gameId, List<String> images);
    Game setGameImages(Game game, List<String> images);
    Game setGameBoxImages(int gameId, List<String> images);
    Game setGameBoxImages(Game game, List<String> images);
    Game deleteGameImage(int gameId, String image);
    Game deleteGameBoxImage(int gameId, String image);
    void deleteGame(int id);
    
}
