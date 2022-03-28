package com.sparta.solo2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordcheck;
    private boolean admin = false;
    private String adminToken = "";
}