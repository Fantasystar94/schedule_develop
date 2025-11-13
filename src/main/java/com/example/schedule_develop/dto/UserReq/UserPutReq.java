package com.example.schedule_develop.dto.UserReq;

import lombok.Getter;

@Getter
public class UserPutReq {
    private String userName;
    private String email;
    public UserPutReq(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
