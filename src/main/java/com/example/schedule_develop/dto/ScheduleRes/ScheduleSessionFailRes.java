package com.example.schedule_develop.dto.ScheduleRes;

import com.example.schedule_develop.dto.common.CommonResponse;
import lombok.Getter;

@Getter
public class ScheduleSessionFailRes extends CommonResponse {
    private final int status;
    private final String message;
    public ScheduleSessionFailRes(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
