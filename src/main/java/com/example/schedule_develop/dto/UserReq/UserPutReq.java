package com.example.schedule_develop.dto.UserReq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserPutReq {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private final String password;
    public UserPutReq(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
