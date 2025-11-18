package com.example.schedule_develop.dto.CommentRes;

import com.example.schedule_develop.dto.ScheduleRes.ScheduleResData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentTotalData<T> {
    private final Long id;
    private final String userName;
    private final String email;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<T> comments;
}
