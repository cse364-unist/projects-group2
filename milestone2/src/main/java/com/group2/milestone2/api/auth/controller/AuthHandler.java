package com.group2.milestone2.api.auth.controller;

import com.group2.milestone2.api.auth.dto.SignInRequestDto;
import com.group2.milestone2.api.auth.dto.SignInResponse;
import com.group2.milestone2.api.auth.dto.SignUpRequestDto;
import com.group2.milestone2.api.auth.service.AuthService;
import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.User;
import com.group2.milestone2.domain.user.repository.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthService authService;
    @Transactional
    @PostMapping(path = "/signup")
    public void signUp(SignUpRequestDto request){
        authService.signUp(request);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<SignInResponse> signIn(SignInRequestDto request){
        return ResponseEntity.ok(authService.signIn(request));
    }




}
