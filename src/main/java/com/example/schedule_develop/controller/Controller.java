package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.ScheduleRes.ScheduleCreateRes;
import com.example.schedule_develop.dto.common.CommonResponse;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule_Develop")
    public ResponseEntity<CommonResponse> postApi(@RequestBody ScheduleCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(HttpStatus.CREATED.value(),"postResponse",req));
    }

    @GetMapping("/schedule_Develop")
    public ResponseEntity<CommonResponse> getApi() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(HttpStatus.OK.value(),"getResponse"));
    }

    @GetMapping("/schedule_Develop/{scheduleID}")
    public ResponseEntity<CommonResponse> getApiDetail(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findDetail(HttpStatus.OK.value(),"getDetialResponse", scheduleID));
    }

    @PutMapping("/schedule_Develop/{scheduleID}")
    public ResponseEntity<CommonResponse> putApi(@PathVariable Long scheduleID, @RequestBody SchedulePutReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.put(HttpStatus.OK.value(),"putResponse", scheduleID, req));
    }

    @DeleteMapping("/schedule_Develop/{scheduleID}")
    public ResponseEntity<CommonResponse> deleteApi(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scheduleService.delete(HttpStatus.OK.value(),"DeleteResponse", scheduleID));
    }

}
