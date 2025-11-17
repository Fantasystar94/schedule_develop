package com.example.schedule_develop.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginFailException extends RuntimeException {
    private final int status;
    public LoginFailException(int status, String message) {
        super(message);
        this.status = status;
    }

}
