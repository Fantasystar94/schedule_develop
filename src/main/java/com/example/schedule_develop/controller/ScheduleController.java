package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.ScheduleRes.ScheduleResData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.schedule_develop.config.enums.ErrorCode.LOGIN_REQUIRED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> postApi(@Valid @RequestBody ScheduleCreateReq req, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("LOGIN_USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(HttpStatus.CREATED.value(), "postResponse", req, id));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<ScheduleResData>>> getApi() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(HttpStatus.OK.value(), "getResponse"));
    }

    @GetMapping("/{scheduleID}")
    public ResponseEntity<GlobalResponse<?>> getApiDetail(@PathVariable(required = false) Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findDetail(HttpStatus.OK.value(), "getResponse", scheduleID));
    }

    @PutMapping("/{scheduleID}")
    public ResponseEntity<GlobalResponse<?>> putApi(@PathVariable Long scheduleID, @Valid @RequestBody SchedulePutReq req, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("LOGIN_USER");
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.put(HttpStatus.OK.value(), "putResponse", scheduleID, id, req));
    }

    @DeleteMapping("/{scheduleID}")
    public ResponseEntity<GlobalResponse<Void>> deleteApi(@PathVariable Long scheduleID) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.delete(HttpStatus.NO_CONTENT.value(), "DeleteResponse", scheduleID));
    }

}
