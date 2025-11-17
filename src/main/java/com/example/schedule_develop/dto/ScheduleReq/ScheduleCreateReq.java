package com.example.schedule_develop.dto.ScheduleReq;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ScheduleCreateReq {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
