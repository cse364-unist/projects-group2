package com.group2.milestone2.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
    private String userId;

    private String password1;
    private String password2;

}
