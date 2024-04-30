package com.group2.milestone2.domain.line_quote.repository;

import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineQuoteRepository extends JpaRepository<LineQuote, Long> {

}
