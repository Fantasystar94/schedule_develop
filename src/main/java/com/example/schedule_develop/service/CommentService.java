package com.example.schedule_develop.service;

import com.example.schedule_develop.config.exception.CommentNotFoundException;
import com.example.schedule_develop.config.exception.ScheduleNotFoundException;
import com.example.schedule_develop.dto.CommentReq.CommentCreateReq;
import com.example.schedule_develop.dto.CommentReq.CommentPutReq;
import com.example.schedule_develop.dto.CommentRes.CommentResData;
import com.example.schedule_develop.dto.CommentRes.CommentTotalData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.Comment;
import com.example.schedule_develop.entity.Schedule;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.CommentRepository;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.schedule_develop.config.enums.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public GlobalResponse<CommentTotalData<CommentResData>> create(int status, String message, CommentCreateReq req, Long scheduleId) {
            Schedule schedule = scheduleRepository
                    .findById(scheduleId)
                    .orElseThrow(()-> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND));
            User user = userRepository
                    .findById(schedule.getUser().getId())
                    .orElseThrow(() -> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND));
            Comment comment = new Comment(req.getContent(), schedule, user);
            commentRepository.save(comment);
            CommentResData commentResData = new CommentResData(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            List<CommentResData> comments = new ArrayList<>();
            comments.add(commentResData);
            CommentTotalData<CommentResData> data = new CommentTotalData<>(
                    schedule.getScheduleId(),
                    schedule.getUser().getUserName(),
                    schedule.getUser().getEmail(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt(),
                    comments
            );
        return new GlobalResponse<>(status, message, data);
    }

    @Transactional
    public GlobalResponse<CommentTotalData<List<CommentResData>>> findAll(int status, String message, Long scheduleId) {
        Schedule schedule = scheduleRepository
                .findById(scheduleId)
                .orElseThrow(()-> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND));
        if (schedule.getComments().isEmpty()) {
            throw new CommentNotFoundException(COMMENT_NOT_FOUND);
        }
        List<CommentResData> commentList = schedule.getComments().stream().map( comment ->
                new CommentResData(
                  comment.getId(),
                  comment.getContent(),
                  comment.getCreatedAt(),
                  comment.getUpdatedAt()
                )).toList();
        CommentTotalData<List<CommentResData>> data = new CommentTotalData(
                schedule.getScheduleId(),
                schedule.getUser().getUserName(),
                schedule.getUser().getEmail(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                commentList
        );
        return new GlobalResponse<>(status, message, data);
    }

//    put(HttpStatus.CREATED.value(), "putResponse", scheduleId, commentId, req));
    @Transactional
    public GlobalResponse<CommentResData> put(int status, String message, Long scheduleId, Long commentId ,CommentPutReq req) {

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException(COMMENT_NOT_FOUND));
        if (!comment.getSchedule().getScheduleId().equals(scheduleId)) {
             throw new ScheduleNotFoundException(COMMENT_SCHEDULE_NOT_MATCH);
        }
        comment.update(req.getContent());
        return new GlobalResponse<>(
            status,
            message,
            new CommentResData(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt())
        );
    }

    @Transactional
    public GlobalResponse<Void> delete(int status, String message, Long scheduleId, Long commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException(COMMENT_NOT_FOUND));
        if (!comment.getSchedule().getScheduleId().equals(scheduleId)) {
            throw new ScheduleNotFoundException(COMMENT_SCHEDULE_NOT_MATCH);
        }
        if (commentRepository.existsById(comment.getId())) {
            commentRepository.deleteById(comment.getId());
        }
        return GlobalResponse.successNodata(status, message);
    }
}
