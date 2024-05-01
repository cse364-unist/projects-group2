package com.group2.milestone2.api.lines.service;

import com.group2.milestone2.api.lines.dto.LineDto;
import com.group2.milestone2.api.lines.dto.SearchLinesRequestDto;
import com.group2.milestone2.api.lines.dto.SearchLinesResponse;
import com.group2.milestone2.common.AuthHandler;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "transactionManager", readOnly = true)
@RequiredArgsConstructor
public class LinesService {

    private final SessionRepository sessionRepository;
    private final LineQuoteRepository lineQuoteRepository;

    public SearchLinesResponse searchLines(SearchLinesRequestDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow(RuntimeException::new);

        List<LineQuote> lineQuoteByQuery = lineQuoteRepository.findLineQuoteByQuery(requestDto.getTags(), requestDto.getFavorite(), user);

        List<LineDto> lineDtos = lineQuoteByQuery.stream()
            .map(lineQuote -> new LineDto(
                lineQuote.getId(),
                lineQuote.getActor(),
                lineQuote.getContent(),
                lineQuote.getMovie().getTitle(),
                lineQuote.getLineTagList().stream().map(LineTag::getContent).collect(Collectors.toList()),
                (long) lineQuote.getFavoriteUsers().size(),
                lineQuote.getFavoriteUsers().contains(user)
            )).toList();
        return new SearchLinesResponse(lineDtos);
    }
}
