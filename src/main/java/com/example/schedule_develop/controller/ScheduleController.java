package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.ScheduleRes.ScheduleResData;
import com.example.schedule_develop.dto.common.CommonResponse;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule_Develop/schedules")
    public ResponseEntity<GlobalResponse<?>> postApi(@RequestBody ScheduleCreateReq req, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(scheduleService.sessionFail(HttpStatus.UNAUTHORIZED.value(), "session fail"));
        } else {
            Long id = (Long) session.getAttribute("LOGIN_USER");
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(HttpStatus.CREATED.value(),"postResponse", req, id));
        }
    }

    @GetMapping("/schedule_Develop/schedules")
    public ResponseEntity<GlobalResponse<List<ScheduleResData>>> getApi() {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(HttpStatus.OK.value(),"getResponse"));
    }

    @GetMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<GlobalResponse<?>> getApiDetail(@PathVariable(required = false) Long scheduleID) {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findDetail(HttpStatus.OK.value(),"getResponse",scheduleID));
    }

    @PutMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<GlobalResponse<?>> putApi(@PathVariable Long scheduleID, @RequestBody SchedulePutReq req,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(scheduleService.sessionFail(HttpStatus.UNAUTHORIZED.value(), "session fail"));
        } else {
            Long id = (Long) session.getAttribute("LOGIN_USER");
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.put(HttpStatus.OK.value(),"putResponse", scheduleID, id, req));
        }
    }

    @DeleteMapping("/schedule_Develop/schedules/{scheduleID}")
    public ResponseEntity<GlobalResponse<Void>> deleteApi(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(HttpStatus.NO_CONTENT.value(),"DeleteResponse", scheduleID));
    }


}
