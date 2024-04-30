package com.group2.milestone2.api.auth.controller;

import com.group2.milestone2.api.auth.dto.SignInRequestDto;
import com.group2.milestone2.api.auth.dto.SignInResponse;
import com.group2.milestone2.api.auth.dto.SignUpRequestDto;
import com.group2.milestone2.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public void signUp(@RequestBody SignUpRequestDto requestDto) {
        authService.signUp(requestDto);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequestDto requestDto) {
        return ResponseEntity.ok(authService.signIn(requestDto));
    }

    @GetMapping("/signout")
    public void signOut(@CookieValue String session) {
        authService.signOut(session);
    }


}
