package com.group2.milestone2.domain.session.repository;

import com.group2.milestone2.domain.session.domain.QSession;
import com.group2.milestone2.domain.session.domain.Session;
import com.group2.milestone2.domain.user.domain.TheUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@RequiredArgsConstructor
public class SessionRepositoryCustomImpl implements SessionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Session> findByUserId(String userId) {
        QSession session = QSession.session;

        return Optional.ofNullable(queryFactory.selectFrom(session)
            .where(session.user.userId.eq(userId))
            .fetchOne());
    }

    @Override
    public Optional<TheUser> findUserByContent(String content) {
        QSession session = QSession.session;

        return Optional.ofNullable(queryFactory.select(session.user)
            .where(session.content.eq(content))
            .fetchOne());
    }
}
