package com.orejita.games.DAO.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.User.Collection;

@Repository
public interface ICollectionDao extends JpaRepository<Collection, Long> {

    Optional<Collection> findByUserId(Long id);
    Optional<Collection> findByUserUsername(String username);
    
}
