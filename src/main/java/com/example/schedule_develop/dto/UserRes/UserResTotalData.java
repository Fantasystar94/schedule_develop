package com.example.schedule_develop.dto.UserRes;

import com.example.schedule_develop.dto.ScheduleRes.ScheduleResData;

import java.time.LocalDateTime;
import java.util.List;

public class UserResTotalData {
    private final List<UserResData> userResData;
    private final List<ScheduleResData> scheduleResData;
    public UserResTotalData(List<UserResData> userResData, List<ScheduleResData> scheduleResData) {
        this.userResData = userResData;
        this.scheduleResData = scheduleResData;
    }

}
