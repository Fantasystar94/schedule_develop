package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.CommentReq.CommentCreateReq;
import com.example.schedule_develop.dto.CommentRes.CommentResData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepoo;

    public GlobalResponse<CommentResData> create(CommentCreateReq req) {

        return null;
    }
}
