package com.group2.milestone2.api;

import com.group2.milestone2.api.my_line.dto.LineCandidateIdDto;
import com.group2.milestone2.api.util.dto.AddFavoriteRequestDto;
import com.group2.milestone2.api.util.service.UtilService;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.line_tag.repository.LineTagRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilTest {

    @Mock
    private LineTagRepository lineTagRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private LineQuoteRepository lineQuoteRepository;
    @InjectMocks
    private UtilService utilService;

    @Test
    void getTagListNormal(){
        Mockito.when(lineTagRepository.findAll())
            .thenReturn(new ArrayList<>());
        Assertions.assertDoesNotThrow(()->utilService.getTagList());
    }

    @Test
    void getTagListExists(){
        List<LineTag> lineTagList = new ArrayList<>();
        lineTagList.add(LineTag.create("normalTag"));
        Mockito.when(lineTagRepository.findAll())
            .thenReturn(lineTagList);
        Assertions.assertDoesNotThrow(()->utilService.getTagList());
    }

    @Test
    void addFavoriteNormal(){
        LineQuote lineQuote = LineQuote.create("content", "actor", Movie.create("title",null,null,null),new ArrayList<>());
        TheUser theUser = new TheUser();
        theUser.setFavoriteLines(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findById(1L))
            .thenReturn(Optional.of(lineQuote));

        AddFavoriteRequestDto requestDto = new AddFavoriteRequestDto(1L);

        Assertions.assertDoesNotThrow(() -> utilService.addFavorite(requestDto, "normalSession"));
    }

    @Test
    void addFavoriteNotFound(){
        TheUser theUser = new TheUser();
        List<LineQuote> favoriteLines = new ArrayList<>();
        theUser.setFavoriteLines(favoriteLines);
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findById(1L))
            .thenReturn(Optional.empty());

        AddFavoriteRequestDto requestDto = new AddFavoriteRequestDto(1L);

        Assertions.assertThrows(Exception.class, () -> utilService.addFavorite(requestDto, "normalSession"));
    }

    @Test
    void removeFavoriteNormal(){
        LineQuote lineQuote = LineQuote.create("content", "actor", Movie.create("title",null,null,null),new ArrayList<>());
        TheUser theUser = new TheUser();
        List<LineQuote> favoriteLines = new ArrayList<>();
        favoriteLines.add(lineQuote);
        theUser.setFavoriteLines(favoriteLines);
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findById(1L))
            .thenReturn(Optional.of(lineQuote));

        AddFavoriteRequestDto requestDto = new AddFavoriteRequestDto(1L);

        Assertions.assertDoesNotThrow(() -> utilService.removeFavorite(requestDto, "normalSession"));
    }

    @Test
    void removeFavoriteNotFound(){
        TheUser theUser = new TheUser();
        List<LineQuote> favoriteLines = new ArrayList<>();
        theUser.setFavoriteLines(favoriteLines);
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineQuoteRepository.findById(1L))
            .thenReturn(Optional.empty());

        AddFavoriteRequestDto requestDto = new AddFavoriteRequestDto(1L);

        Assertions.assertThrows(Exception.class, () -> utilService.removeFavorite(requestDto, "normalSession"));
    }
}
