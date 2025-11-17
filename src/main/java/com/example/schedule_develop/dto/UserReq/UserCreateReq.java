package com.example.schedule_develop.dto.UserReq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateReq {
    @NotBlank
    public String userName;
    @NotBlank
    @Email
    public String email;
    @NotBlank
    public String password;
}
