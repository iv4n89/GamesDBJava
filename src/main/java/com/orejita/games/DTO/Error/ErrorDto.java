package com.orejita.games.DTO.Error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDto {
    
    private String message;
    private HttpStatus status;

}
