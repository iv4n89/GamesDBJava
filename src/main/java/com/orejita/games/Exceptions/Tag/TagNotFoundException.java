package com.orejita.games.Exceptions.Tag;

public class TagNotFoundException extends RuntimeException {
    
    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }

}
