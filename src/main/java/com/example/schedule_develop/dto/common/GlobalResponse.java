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

    public static <T> GlobalResponse<T> success(int status, String message, T data) {
        return new GlobalResponse<>(status, message, data);
    }

    public static <T> GlobalResponse<T> fail(int status, String message) {
        return new GlobalResponse<>(status, message, null);
    }

}
