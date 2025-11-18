package com.example.schedule_develop.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final int status;
    private final String message;
    public ExceptionResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
