package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.User.UserGameStatic;

public interface IUserGameStaticService {
    
    List<UserGameStatic> getAllUserGameStatics(long userId);
    List<UserGameStatic> getAllUserGameStaticsByGame(long gameId);
    UserGameStatic getOneUserGameStatic(long id);
    UserGameStatic createUserGameStatic(long userId, UserGameStatic userGameStatic);
    UserGameStatic updateUserGameStatic(long id, UserGameStatic userGameStatic);
    void deleteUserGameStatic(long id);

}
