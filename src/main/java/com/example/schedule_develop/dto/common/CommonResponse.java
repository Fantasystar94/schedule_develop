package com.example.schedule_develop.dto.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class CommonResponse {
    protected int status;
    protected String message;

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
