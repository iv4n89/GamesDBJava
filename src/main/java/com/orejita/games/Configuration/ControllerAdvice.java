package com.orejita.games.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.orejita.games.DTO.Error.ErrorDto;
import com.orejita.games.Exceptions.API.ApiException;

@RestControllerAdvice
public class ControllerAdvice {
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException ex) {
        ErrorDto error = ErrorDto.builder().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException(ApiException ex) {
        ErrorDto error = ErrorDto.builder().message(ex.getMessage()).status(ex.getStatus()).build();
        return new ResponseEntity<ErrorDto>(error, error.getStatus());
    }

}
