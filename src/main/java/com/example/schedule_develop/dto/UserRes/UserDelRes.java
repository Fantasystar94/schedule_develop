package com.example.schedule_develop.dto.UserRes;

import lombok.Getter;

@Getter
public class UserDelRes {
    private Long id;
    private String userName;

    public UserDelRes(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
