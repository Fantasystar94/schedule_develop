package com.example.schedule_develop.config.exception;

import com.example.schedule_develop.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleException(IllegalStateException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청 오류 : " + e.getMessage());
//    }
//    not Found 상속구조... notFoundException -> schedue,user,comment...
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getStatus(), e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getStatus(), e.getMessage()));
    }

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<ExceptionResponse> loginFailException(LoginFailException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getStatus(), e.getMessage()));
    }
}
