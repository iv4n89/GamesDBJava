package com.orejita.games.DAO.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Comment.Comment;

@Repository
public interface ICommentDao extends JpaRepository<Comment, Long> {
    
    List<Comment> findAllByConsoleId(Long consoleId);
    List<Comment> findAllByGameId(Long gameId);
    List<Comment> findAllByUserId(Long userId);

}
