package com.example.schedule_develop.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ScheduleNotFoundException extends NotFoundException{
    public ScheduleNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
