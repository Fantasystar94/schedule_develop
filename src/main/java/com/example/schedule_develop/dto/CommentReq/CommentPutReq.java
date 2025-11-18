package com.example.schedule_develop.dto.CommentReq;

import lombok.Getter;

@Getter
public class CommentPutReq {
    private String content;
    CommentPutReq(String content) {
        this.content = content;
    }
}
