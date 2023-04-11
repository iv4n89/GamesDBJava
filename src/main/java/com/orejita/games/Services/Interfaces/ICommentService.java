package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.DTO.Comment.CommentDto;
import com.orejita.games.Entities.Comment.Comment;
import com.orejita.games.Services.IService;

public interface ICommentService extends IService<Comment> {
    
    List<Comment> getAllComments();
    List<Comment> getAllCommentsByConsoleId(long consoleId);
    List<Comment> getAllCommentsByGameId(long gameId);
    List<Comment> getAllCommentsByUserId(long userId);
    Comment getOneComment(long id);
    Comment createCommentForConsole(long userId, long consoleId, Comment comment);
    Comment createCommentForGame(long userId, long gameId, Comment comment);
    Comment createComment(long userId, Comment comment);
    Comment updateComment(long id, Comment comment);
    void deleteComment(long id);

}
