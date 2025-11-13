package com.example.schedule_develop.service;
import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;

public interface CommonService<CommonResponse> {
    CommonResponse create(int status, String message, ScheduleCreateReq req);

    CommonResponse findAll(int status, String message);

    CommonResponse findDetail(int status, String message, Long scheduleID);

    CommonResponse put(int status, String message, Long id , SchedulePutReq req);

    CommonResponse delete(int status, String message, Long id);
}
