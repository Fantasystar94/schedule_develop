package com.example.schedule_develop.dto.ScheduleReq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePutReq {
    private final String title;
    private final String content;
}
