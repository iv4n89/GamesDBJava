package com.orejita.games.Exceptions.Game;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
    
}
