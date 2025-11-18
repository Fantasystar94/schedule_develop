package com.example.schedule_develop.config.exception;

import com.example.schedule_develop.config.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginFailException extends RuntimeException {
    private final int status;
    public LoginFailException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

}
