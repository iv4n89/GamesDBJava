package com.orejita.games.Services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.DAO.User.IUserDao;
import com.orejita.games.DAO.User.IUserGameStaticDao;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Entities.User.UserGameStatic;
import com.orejita.games.Services.Interfaces.IUserGameStaticService;

@Service
public class UserGameStaticService implements IUserGameStaticService {
    
    @Autowired
    private IUserGameStaticDao dao;

    @Autowired
    private IGameDao gameDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public List<UserGameStatic> getAllUserGameStatics(long userId) {
        return dao.findByUserId(userId).orElse(null);
    }

    @Override
    public List<UserGameStatic> getAllUserGameStaticsByGame(long gameId) {
        return dao.findByGameId(gameId).orElse(null);
    }

    @Override
    public UserGameStatic getOneUserGameStatic(long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public UserGameStatic createUserGameStatic(long userId, UserGameStatic userGameStatic) {
        User user = userDao.findById(userId).orElse(null);
        userGameStatic.setUser(user);
        return dao.save(userGameStatic);
    }

    @Override
    public UserGameStatic updateUserGameStatic(long id, UserGameStatic userGameStatic) {
        UserGameStatic gameStatic = this.getOneUserGameStatic(id);

        if (gameStatic == null) {
            return null;
        }

        if (userGameStatic.getPlayedHours() != null) {
            gameStatic.setPlayedHours(userGameStatic.getPlayedHours());
        }
        if (userGameStatic.getStatus() != null) {
            gameStatic.setStatus(userGameStatic.getStatus());
        }
        if (userGameStatic.getFavorite() != null) {
            gameStatic.setFavorite(userGameStatic.getFavorite());
        }
        
        return dao.save(gameStatic);
    }

    @Override
    public void deleteUserGameStatic(long id) {
        dao.deleteById(id);
    }



}
