package com.group2.milestone2.common;


import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.session.repository.SessionRepositoryCustom;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthHandler {

    private final SessionRepository sessionRepository;

    public Optional<TheUser> authorizeSession(String sessionString) {
        return sessionRepository.findUserByContent(sessionString);
    }
}
