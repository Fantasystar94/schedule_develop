package com.example.schedule_develop.dto.UserReq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginReq {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

}
