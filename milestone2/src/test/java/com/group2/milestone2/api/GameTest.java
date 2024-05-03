package com.group2.milestone2.api;

import static org.mockito.ArgumentMatchers.any;

import com.group2.milestone2.api.game.service.GameService;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameTest {

    @Mock
    private LineQuoteRepository lineQuoteRepository;
    @InjectMocks
    private GameService gameService;

    @Test
    void getTitleFromDataNormal() {
        Mockito.when(lineQuoteRepository.count())
            .thenReturn(20L);
        Mockito.when(lineQuoteRepository.findById(any()))
            .thenReturn(Optional.of(LineQuote.create(
                "content", "actor",
                Movie.create("title", null, null, null),
                null
            )));

        Assertions.assertDoesNotThrow(() -> gameService.getTitleFromLineData(10L));
    }

    @Test
    void getTitleFromDataNotExists() {
        Mockito.when(lineQuoteRepository.count())
            .thenReturn(20L);
        Mockito.when(lineQuoteRepository.findById(any()))
            .thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> gameService.getTitleFromLineData(10L));
    }

    @Test
    void getActorFromDataNormal() {
        Mockito.when(lineQuoteRepository.count())
            .thenReturn(20L);
        Mockito.when(lineQuoteRepository.findById(any()))
            .thenReturn(Optional.of(LineQuote.create(
                "content", "actor",
                Movie.create("title", null, null, null),
                null
            )));

        Assertions.assertDoesNotThrow(() -> gameService.getActorFromLineData(10L));
    }

    @Test
    void getActorFromDataNotExists() {
        Mockito.when(lineQuoteRepository.count())
            .thenReturn(20L);
        Mockito.when(lineQuoteRepository.findById(any()))
            .thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> gameService.getActorFromLineData(10L));
    }
}
