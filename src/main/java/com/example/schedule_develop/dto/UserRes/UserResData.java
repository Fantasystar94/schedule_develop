package com.example.schedule_develop.dto.UserRes;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResData {

    private Long id;
    private String userName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResData(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
