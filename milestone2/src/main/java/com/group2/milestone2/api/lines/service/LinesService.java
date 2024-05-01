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
//        tags: string[]  // nullable. 길이가 0~3인 검색할 태그 리스트로 보내주면 됨. args로 리스트 보내는 법은 ?tags=어쩌구&tags=저쩌구&... 하면 되는걸로 앎. 이러면 spring에서 tags=[“어쩌구”, “저쩌구”] 로 해석할 수 있음
//        favorite: bool //?favorite:이 true, 1, yes면 spring에서 true로 해석하고, false, 0, no면 false로 해석함
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
