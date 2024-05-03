package com.group2.milestone2.domain.line_candidate.repository;

import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.domain.QLineCandidate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LineCandidateRepositoryCustomImpl implements LineCandidateRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LineCandidate> findOlderThanNow(){
        LocalDateTime now = LocalDateTime.now();

        QLineCandidate lineCandidate = QLineCandidate.lineCandidate;

        return jpaQueryFactory
            .selectFrom(lineCandidate)
            .where(lineCandidate.expireAt.lt(now))
            .fetch();
    }

}
