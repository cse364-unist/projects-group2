package com.group2.milestone2.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequestDto {
    private String userId;
    private String password;
}
