package com.example.schedule_develop.dto.ScheduleRes;

import com.example.schedule_develop.dto.common.CommonResponse;
import lombok.Getter;

@Getter
public class ScheduleReadRes<T> extends CommonResponse {
    private final T data;

    public ScheduleReadRes(int status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}
