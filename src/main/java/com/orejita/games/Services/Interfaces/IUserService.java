package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.User.User;

public interface IUserService {
    
    List<User> getAllUsers();
    User getOneUser(Long id);
    User getOneUser(String username);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

}
