package com.example.schedule_develop.config.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "없는 유저 아이디 입니다."),
    SCHEDULE_NOT_FOUND(404, "일정이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(404, "댓글이 존재하지 않습니다"),
    INVALID_INPUT(400, "입력값이 올바르지 않습니다."),
    USER_NOT_MATCH(403,"접근권한이 없습니다."),
    LOGIN_FAIL(401,"이메일 혹은 비밀번호가 일치하지 않습니다."),
    LOGIN_REQUIRED(401, "로그인한 유저만 사용할 수 있는 기능입니다."),
    COMMENT_SCHEDULE_NOT_MATCH(400, "해당 일정에 댓글이 존재하지 않습니다");
    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
