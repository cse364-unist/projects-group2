package com.group2.milestone2.domain.session.repository;

import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.Optional;

public interface SessionRepositoryCustom {
    Optional<Session> findByUserId(String userId);

    Optional<TheUser> findUserByContent(String content);
}
