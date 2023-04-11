package com.orejita.games.Exceptions.API;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private HttpStatus status;
    
    public ApiException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, Throwable reason) {
        super(message, reason);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, Throwable reason, HttpStatus status) {
        super(message, reason);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
    
}
