package com.example.schedule_develop.config;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "없는 유저 아이디 입니다."),
    SCHEDULE_NOT_FOUND(404, "일정이 존재하지 않습니다."),
    INVALID_INPUT(400, "입력값이 올바르지 않습니다."),
    USER_NOT_MATCH(403,"접근권한이 없습니다.");
    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
