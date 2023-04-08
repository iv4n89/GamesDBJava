package com.orejita.games.DAO.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.User.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
}
