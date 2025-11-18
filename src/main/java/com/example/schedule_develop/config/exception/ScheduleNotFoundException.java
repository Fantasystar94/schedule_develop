package com.example.schedule_develop.config.exception;

import com.example.schedule_develop.config.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ScheduleNotFoundException extends NotFoundException {

    public ScheduleNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getStatus());
    }
}
