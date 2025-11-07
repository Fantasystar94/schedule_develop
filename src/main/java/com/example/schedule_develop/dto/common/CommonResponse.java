package com.example.schedule_develop.dto.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class CommonResponse {
    protected int status;
    protected String message;

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
