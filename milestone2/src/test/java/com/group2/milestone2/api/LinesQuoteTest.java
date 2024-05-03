package com.group2.milestone2.api;

import static org.mockito.ArgumentMatchers.any;

import com.group2.milestone2.api.lines.dto.SearchLinesRequestDto;
import com.group2.milestone2.api.lines.service.LinesService;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.domain.QLineCandidate;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.domain.QLineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinesQuoteTest {

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private LineQuoteRepository lineQuoteRepository;
    @InjectMocks
    private LinesService linesService;

    @Test
    void searchLinesNormal() {
        TheUser theUser = new TheUser();
        theUser.setFavoriteLines(List.of());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findLineQuoteByQuery(null, null, TheUser.create("id", "pw")))
            .thenReturn(List.of());

        SearchLinesRequestDto requestDto = new SearchLinesRequestDto(null, null);

        Assertions.assertDoesNotThrow(() -> linesService.searchLines(requestDto, "normalSession"));
    }

    @Test
    void searchLinesFound() {
        TheUser theUser = new TheUser();
        theUser.setFavoriteLines(List.of());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findLineQuoteByQuery(null, null, TheUser.create("id", "pw")))
            .thenReturn(List.of(LineQuote.create("line", "actor", Movie.create("title", null, null, null), null)));

        SearchLinesRequestDto requestDto = new SearchLinesRequestDto(null, null);

        Assertions.assertDoesNotThrow(() -> linesService.searchLines(requestDto, "normalSession"));
    }

    @Mock
    PathInits pathInits;
    @Mock
    PathMetadata pathMetadata;
    @Test
    void qLineQuoteTrue(){
        Mockito.when(pathInits.isInitialized("movie"))
            .thenReturn(true);
        Assertions.assertDoesNotThrow(()->new QLineQuote(LineQuote.class, pathMetadata, pathInits));
    }
    @Test
    void qLineQuoteFalse(){
        Mockito.when(pathInits.isInitialized("movie"))
            .thenReturn(false);
        Assertions.assertDoesNotThrow(()->new QLineQuote(LineQuote.class, pathMetadata, pathInits));
    }

}
