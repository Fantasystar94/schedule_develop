package com.example.schedule_develop.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
    private final int status;
    public NotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

}
