package com.orejita.games.DAO.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.User.UserGameStatic;

@Repository
public interface IUserGameStaticDao extends JpaRepository<UserGameStatic, Long> {

    Optional<List<UserGameStatic>> findByUserId(Long id);
    Optional<List<UserGameStatic>> findByUserUsername(String username);
    Optional<List<UserGameStatic>> findByGameId(Long id);
    
}
