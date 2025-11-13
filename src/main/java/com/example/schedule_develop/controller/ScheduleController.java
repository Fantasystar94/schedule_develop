package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.common.CommonResponse;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule_Develop/schedules")
    public ResponseEntity<CommonResponse> postApi(@RequestBody ScheduleCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(HttpStatus.CREATED.value(),"postResponse",req));
    }

    @GetMapping("/schedule_Develop/schedules")
    public ResponseEntity<CommonResponse> getApi() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(HttpStatus.OK.value(),"getResponse"));
    }

    @GetMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<CommonResponse> getApiDetail(@PathVariable(required = false) Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findDetail(HttpStatus.OK.value(),"getDetailResponse", scheduleID));
    }

    @PutMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<CommonResponse> putApi(@PathVariable Long scheduleID, @RequestBody SchedulePutReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.put(HttpStatus.OK.value(),"putResponse", scheduleID, req));
    }

    @DeleteMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<CommonResponse> deleteApi(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(HttpStatus.NO_CONTENT.value(),"DeleteResponse", scheduleID));
    }


}
