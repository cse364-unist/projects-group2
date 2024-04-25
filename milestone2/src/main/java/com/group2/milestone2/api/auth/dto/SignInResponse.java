package com.group2.milestone2.api.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
public class SignInResponse {

    private String session;
}
