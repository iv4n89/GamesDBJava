package com.orejita.games.Services.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Comment.ICommentDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.DAO.User.IUserDao;
import com.orejita.games.DTO.Comment.CommentDto;
import com.orejita.games.Entities.Comment.Comment;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Exceptions.API.ApiException;
import com.orejita.games.Services.Interfaces.ICommentService;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDao dao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IGameDao gameDao;

    @Autowired
    private IUserDao userDao;

    private final String COMMENT_NOT_FOUND = "Comment not found";
    private final String USER_NEEDED = "User id is needed to post a new comment";
    private final String USER_NOT_FOUND = "User not found";
    private final String CONSOLE_NOT_FOUND = "Console not found";
    private final String GAME_NOT_FOUND = "Game not found";
    private final String ONLY_ONE_NO_ONE = "The comment must be sent for a certain console or game";

    @Override
    public List<Comment> getAllComments() {
        return this.dao.findAll();
    }

    @Override
    public List<Comment> getAllCommentsByConsoleId(long consoleId) {
        return this.dao.findAllByConsoleId(consoleId);
    }

    @Override
    public List<Comment> getAllCommentsByGameId(long gameId) {
        return this.dao.findAllByGameId(gameId);
    }

    @Override
    public List<Comment> getAllCommentsByUserId(long userId) {
        return this.dao.findAllByUserId(userId);
    }

    @Override
    public Comment getOneComment(long id) {
        return this.dao.findById(id).orElseThrow(() -> new ApiException(this.COMMENT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public Comment createComment(long userId, Comment comment) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND, HttpStatus.BAD_REQUEST));

        comment.setUser(user);
        return dao.save(comment);
    }

    @Override
    public Comment createCommentForConsole(long userId, long consoleId, Comment comment) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND, HttpStatus.BAD_REQUEST));
        
        Console console = consoleDao.findById(consoleId)
                .orElseThrow(() -> new ApiException(CONSOLE_NOT_FOUND, HttpStatus.BAD_REQUEST));
            
        comment.setUser(user);
        comment.setConsole(console);
        return dao.save(comment);
    }

    @Override
    public Comment createCommentForGame(long userId, long gameId, Comment comment) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND, HttpStatus.BAD_REQUEST));

        Game game = gameDao.findById(gameId)
                .orElseThrow(() -> new ApiException(GAME_NOT_FOUND, HttpStatus.BAD_REQUEST));

        comment.setUser(user);
        comment.setGame(game);
        return dao.save(comment);
    }

    @Override
    public Comment updateComment(long id, Comment comment) {
        Comment _comment = this.getOneComment(id);

        if (comment.getComment() != null) {
            _comment.setComment(comment.getComment());
        }

        return this.dao.save(_comment);
    }

    @Override
    public void deleteComment(long id) {
        this.dao.deleteById(id);
    }

}
