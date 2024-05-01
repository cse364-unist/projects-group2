package com.group2.milestone2.domain.line_quote.repository;

import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.domain.QLineQuote;
import com.group2.milestone2.domain.line_tag.domain.QLineTag;
import com.group2.milestone2.domain.user.domain.QTheUser;
import com.group2.milestone2.domain.user.domain.TheUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LineQuoteRepositoryCustomImpl implements LineQuoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LineQuote> findLineQuoteByQuery(List<String> tags, Boolean favorite, TheUser user) {
        QLineQuote lineQuote = QLineQuote.lineQuote;
        QLineTag lineTag = QLineTag.lineTag;
        QTheUser theUser = QTheUser.theUser;

        BooleanExpression conditions = Expressions.TRUE;

        if (tags != null) {
            conditions  = conditions.and(lineTag.content.in(tags));
        }

        if (favorite != null && favorite) {
            conditions  = conditions.and(lineQuote.favoriteUsers.contains(user));
        }

        return jpaQueryFactory.select(lineQuote)
            .from(lineQuote)
            .leftJoin(lineQuote.lineTagList, lineTag)
            .leftJoin(lineQuote.favoriteUsers, theUser)
            .where(conditions)
            .orderBy(lineQuote.favoriteUsers.size().desc())
            .fetch();
    }
}
