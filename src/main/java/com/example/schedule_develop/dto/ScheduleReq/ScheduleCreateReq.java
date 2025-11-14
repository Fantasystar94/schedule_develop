package com.example.schedule_develop.dto.ScheduleReq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ScheduleCreateReq {
    private String title;
    private String content;
}
