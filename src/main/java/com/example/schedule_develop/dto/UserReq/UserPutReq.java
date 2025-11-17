package com.example.schedule_develop.dto.UserReq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserPutReq {
    @NotBlank
    @Min(4)
    private String userName;
    @NotBlank
    @Email
    private String email;
    public UserPutReq(String userName, String email) {

        this.userName = userName;
        this.email = email;
    }
}
