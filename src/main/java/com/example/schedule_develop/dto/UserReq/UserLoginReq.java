package com.example.schedule_develop.dto.UserReq;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginReq {
    private String email;
    private String password;

}
