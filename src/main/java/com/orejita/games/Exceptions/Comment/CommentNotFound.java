package com.orejita.games.Exceptions.Comment;

public class CommentNotFound extends RuntimeException {

    public CommentNotFound(String message) {
        super(message);
    }

    public CommentNotFound(String message, Throwable reason) {
        super(message, reason);
    }
    
}
