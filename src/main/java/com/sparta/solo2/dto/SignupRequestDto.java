package com.sparta.solo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordcheck;
    private boolean admin = false;
    private String adminToken = "";


    public SignupRequestDto(String username, String password, String passwordcheck) {

        this.username = username;
        this.password = password;
        this.passwordcheck = passwordcheck;

    }
}