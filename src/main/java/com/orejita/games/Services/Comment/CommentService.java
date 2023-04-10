package com.orejita.games.Services.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Comment.ICommentDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.DAO.User.IUserDao;
import com.orejita.games.Entities.Comment.Comment;
import com.orejita.games.Exceptions.Comment.CommentNotFound;
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
        return this.dao.findById(id).orElseThrow(() -> new CommentNotFound("Comment with id " + id + " not found"));
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
