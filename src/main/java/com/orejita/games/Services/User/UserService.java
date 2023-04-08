package com.orejita.games.Services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.User.IUserDao;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Services.Interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao dao;

    @Override
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @Override
    public User getOneUser(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public User getOneUser(String username) {
        return dao.findByUsername(username).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return dao.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User _user = this.getOneUser(id);

        if (_user == null) {
            return null;
        }

        if (user.getName() != null) {
            _user.setName(user.getName());
        }
        if (user.getLastName() != null) {
            _user.setLastName(user.getLastName());
        }
        if (user.getBirthDate() != null) {
            _user.setBirthDate(user.getBirthDate());
        }
        if (user.getUsername() != null) {
            _user.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            _user.setPassword(user.getPassword());
        }
        if (user.getAvatar() != null) {
            _user.setAvatar(user.getAvatar());
        }

        return dao.save(_user);
    }

    @Override
    public void deleteUser(Long id) {
        dao.deleteById(id);
    }


    
}
