package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Comment.Comment;

public interface ICommentService {
    
    List<Comment> getAllComments();
    List<Comment> getAllCommentsByConsoleId(long consoleId);
    List<Comment> getAllCommentsByGameId(long gameId);
    List<Comment> getAllCommentsByUserId(long userId);
    Comment getOneComment(long id);
    Comment updateComment(long id, Comment comment);
    void deleteComment(long id);

}
