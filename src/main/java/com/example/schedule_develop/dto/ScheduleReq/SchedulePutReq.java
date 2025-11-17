package com.example.schedule_develop.dto.ScheduleReq;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePutReq {
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;
}
