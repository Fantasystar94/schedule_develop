package com.example.schedule_develop.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalResponse<T> {
    protected int status;
    protected String message;
    protected T data;

}
