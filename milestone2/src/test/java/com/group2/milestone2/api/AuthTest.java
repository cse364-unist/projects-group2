package com.group2.milestone2.api;

import static org.mockito.ArgumentMatchers.any;

import com.group2.milestone2.api.auth.dto.SignInRequestDto;
import com.group2.milestone2.api.auth.dto.SignUpRequestDto;
import com.group2.milestone2.api.auth.service.AuthService;
import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import com.group2.milestone2.domain.user.repository.TheUserRepository;
import java.util.Optional;
import java.util.OptionalInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class AuthTest {

    @Mock
    private TheUserRepository userRepository;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private AuthService authService;

    @Test
    void signUpNormal() {
        // given
        Mockito.when(userRepository.findById("normalId")).thenReturn(Optional.empty());
        SignUpRequestDto requestDto = new SignUpRequestDto(
            "normalId",
            "normalPassword",
            "normalPassword"
        );

        // when, then
        Assertions.assertDoesNotThrow(() -> authService.signUp(requestDto));

    }

    @Test
    void signUpPasswordMismatch() {
        // given
        Mockito.when(userRepository.findById("normalId")).thenReturn(Optional.empty());
        SignUpRequestDto requestDto = new SignUpRequestDto(
            "normalId",
            "normalPassword",
            "notMatchingPassword"
        );

        // when, then
        Assertions.assertThrows(RuntimeException.class, () -> authService.signUp(requestDto));

    }

    @Test
    void signUpExistingUserId() {
        // given
        Mockito.when(userRepository.findById("normalId")).thenReturn(Optional.of(new TheUser()));
        SignUpRequestDto requestDto = new SignUpRequestDto(
            "normalId",
            "normalPassword",
            "normalPassword"
        );

        // when, then
        Assertions.assertThrows(RuntimeException.class, () -> authService.signUp(requestDto));

    }


    @Test
    void signInNormal() {
        Mockito.when(userRepository.findById(("normalId")))
            .thenReturn(Optional.of(TheUser.create("normalId", "normalPassword")));
        Mockito.when(sessionRepository.findByUserId("normalId"))
            .thenReturn(Optional.of(Session.create("aSession", null)));
        Mockito.when(sessionRepository.findByContent(any()))
            .thenReturn(Optional.empty());

        SignInRequestDto requestDto = new SignInRequestDto(
            "normalId", "normalPassword"
        );

        Assertions.assertDoesNotThrow(() -> authService.signIn(requestDto));
    }

    @Test
    void signInWrongPassword() {
        Mockito.when(userRepository.findById(("normalId")))
            .thenReturn(Optional.of(TheUser.create("normalId", "normalPassword")));
        Mockito.when(sessionRepository.findByUserId("normalId"))
            .thenReturn(Optional.of(Session.create("aSession", null)));
        Mockito.when(sessionRepository.findByContent(any()))
            .thenReturn(Optional.empty());

        SignInRequestDto requestDto = new SignInRequestDto(
            "normalId", "wrongPassword"
        );

        Assertions.assertThrows(Exception.class, () -> authService.signIn(requestDto));
    }

    @Test
    void signInSessionNotFound() {
        Mockito.when(userRepository.findById(("normalId")))
            .thenReturn(Optional.of(TheUser.create("normalId", "normalPassword")));
        Mockito.when(sessionRepository.findByUserId("normalId"))
            .thenReturn(Optional.empty());
        Mockito.when(sessionRepository.findByContent(any()))
            .thenReturn(Optional.empty());

        SignInRequestDto requestDto = new SignInRequestDto(
            "normalId", "normalPassword"
        );

        Assertions.assertDoesNotThrow(() -> authService.signIn(requestDto));
    }

    @Test
    void signInSessionExists() {
        Mockito.when(userRepository.findById(("normalId")))
            .thenReturn(Optional.of(TheUser.create("normalId", "normalPassword")));
        Mockito.when(sessionRepository.findByUserId("normalId"))
            .thenReturn(Optional.of(Session.create("aSession", null)));
        Mockito.when(sessionRepository.findByContent(any()))
            .thenReturn(Optional.of(Session.create("aSession", null)))
            .thenReturn(Optional.empty());

        SignInRequestDto requestDto = new SignInRequestDto(
            "normalId", "normalPassword"
        );

        Assertions.assertDoesNotThrow(() -> authService.signIn(requestDto));
    }


    @Test
    void signOutNormal() {
        Assertions.assertDoesNotThrow(() -> authService.signOut("asd"));
    }

    @Test
    void signOutEmpty() {
        Assertions.assertDoesNotThrow(() -> authService.signOut(""));
    }
}
