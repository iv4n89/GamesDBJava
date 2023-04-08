package com.orejita.games.Exceptions.Console;

public class ConsoleNotFoundException extends RuntimeException {
    
    public ConsoleNotFoundException(String message) {
        super(message);
    }

    public ConsoleNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }

}
