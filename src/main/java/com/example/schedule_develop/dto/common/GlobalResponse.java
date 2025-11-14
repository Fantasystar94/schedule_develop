package com.example.schedule_develop.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse<T> {
    protected int status;
    protected String message;
    protected T data;
    //GlobalResponse 성공시 static 생성자
    public static <T> GlobalResponse<T> success(int status, String message, T data) {
        return new GlobalResponse<>(status, message, data);
    }
    //GlobalResponse 실패시 static 생성자
    public static <T> GlobalResponse<T> fail(int status, String message) {
        return new GlobalResponse<>(status, message, null);
    }
    //GlobalResponse 데이터 없는 성공요청 static 생성자
    public static <T> GlobalResponse<T> successNodata(int status, String message) {
        return new GlobalResponse<>(status, message, null);
    }

}
