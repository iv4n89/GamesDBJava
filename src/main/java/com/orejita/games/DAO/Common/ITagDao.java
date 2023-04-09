package com.orejita.games.DAO.Common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Common.Tag;

@Repository
public interface ITagDao extends JpaRepository<Tag, Long> {
    
    Optional<List<Tag>> findAllByConsoleId(Long consoleId);
    Optional<List<Tag>> findAllByGameId(Long gameId);

}
