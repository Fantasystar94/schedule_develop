package com.example.schedule_develop.config.exception;

import com.example.schedule_develop.config.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getStatus());
    }
}
