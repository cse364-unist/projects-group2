package com.group2.milestone2.domain.line_quote.repository;

import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.List;

public interface LineQuoteRepositoryCustom {
    List<LineQuote> findLineQuoteByQuery(List<String> tags, Boolean favorite, TheUser user);
}
