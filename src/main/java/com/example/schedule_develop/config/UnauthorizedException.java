package com.example.schedule_develop.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends RuntimeException{
    private final HttpStatus status;
    public UnauthorizedException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
