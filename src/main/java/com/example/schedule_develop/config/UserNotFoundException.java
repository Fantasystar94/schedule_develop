package com.example.schedule_develop.config;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
