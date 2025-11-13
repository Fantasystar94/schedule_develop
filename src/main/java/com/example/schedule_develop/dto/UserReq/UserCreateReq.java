package com.example.schedule_develop.dto.UserReq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateReq {
    public String userName;
    public String email;
    public String password;
}
