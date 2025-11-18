package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.CommentReq.CommentCreateReq;
import com.example.schedule_develop.dto.CommentReq.CommentPutReq;
import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.schedule_develop.config.enums.ErrorCode.LOGIN_FAIL;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<GlobalResponse<?>> postApi(@PathVariable Long scheduleId, @Valid @RequestBody CommentCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(HttpStatus.CREATED.value(), "postResponse", req, scheduleId));
    }

    @GetMapping("/{scheduleId}/comments")
    public ResponseEntity<GlobalResponse<?>> postApi(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.findAll(HttpStatus.OK.value(), "getResponse", scheduleId));
    }

    @PutMapping("/{scheduleId}/comments/{commentId}")
    public ResponseEntity<GlobalResponse<?>> putApi(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentPutReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.put(HttpStatus.OK.value(), "putResponse", scheduleId, commentId, req));
    }

    @DeleteMapping("/{scheduleID}/comments/{commentId}")
    public ResponseEntity<GlobalResponse<Void>> deleteApi(@PathVariable Long scheduleID, @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.delete(HttpStatus.NO_CONTENT.value(), "DeleteResponse", scheduleID, commentId));
    }

}
