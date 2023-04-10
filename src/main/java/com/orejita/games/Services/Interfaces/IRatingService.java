package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Rating.Rating;

public interface IRatingService {

    List<Rating> getAllRatings();
    List<Rating> getAllRatingByConsoleId(long consoleId);
    List<Rating> getAllRatingByGameId(long gameId);
    List<Rating> getAllRatingByUserId(long userId);
    Double getMeanRatingByConsoleId(long consoleId);
    Double getMeanRatingByGameId(long gameId);
    Rating getOneRating(long id);
    Rating createRatingByConsoleId(long consoleId, Rating rating);
    Rating createRatingByGameId(long gameId, Rating rating);
    Rating upateRating(long id, Rating rating);
    void deleteRating(long id);
    
}
