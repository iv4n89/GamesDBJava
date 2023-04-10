package com.orejita.games.Exceptions.Category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
    
}
