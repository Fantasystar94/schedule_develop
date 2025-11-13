package com.example.schedule_develop.dto.ScheduleRes;

import com.example.schedule_develop.dto.common.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRes<T> extends CommonResponse {
    private final T data;
    public ScheduleCreateRes(int status, String message, T data) {
        super (status, message);
        this.data = data;
    }
}
