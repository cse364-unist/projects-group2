package com.group2.milestone2.api;

import static org.mockito.ArgumentMatchers.any;

import com.group2.milestone2.api.my_line.dto.LineCandidateIdDto;
import com.group2.milestone2.api.my_line.dto.UploadMyLineRequestDto;
import com.group2.milestone2.api.my_line.service.MyLineService;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.repository.LineCandidateRepository;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.line_tag.repository.LineTagRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.movie.repository.MovieRepository;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyLineServiceTest {

    @Mock
    private LineTagRepository lineTagRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private LineCandidateRepository lineCandidateRepository;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private MyLineService mylineService;

    @Test
    void uploadMyLineNormal() {
        Mockito.when(movieRepository.findByTitle("normalTitle"))
            .thenReturn(Optional.of(Movie.create("normalTitle", null, null, null)));
        Mockito.when(lineTagRepository.findById(any()))
            .thenReturn(Optional.of(LineTag.create("normalTag")));

        List<String> tagStringList = new ArrayList<>();
        tagStringList.add("normalTag");
        UploadMyLineRequestDto requestDto = new UploadMyLineRequestDto(
            "normalLine",
            "normalTitle",
            "normalActor",
            tagStringList
        );

        Assertions.assertDoesNotThrow(() -> {
            mylineService.uploadMyLine(requestDto);
        });
    }

    @Test
    void uploadMyLineNotExists() {
        Mockito.when(movieRepository.findByTitle("normalTitle"))
            .thenReturn(Optional.empty());
        Mockito.when(lineTagRepository.findById(any()))
            .thenReturn(Optional.empty());

        List<String> tagStringList = new ArrayList<>();
        tagStringList.add("normalTag");
        UploadMyLineRequestDto requestDto = new UploadMyLineRequestDto(
            "normalLine",
            "normalTitle",
            "normalActor",
            tagStringList
        );

        Assertions.assertDoesNotThrow(() -> {
            mylineService.uploadMyLine(requestDto);
        });
    }

    @Test
    void getMyLineBoardTestNormal() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), new ArrayList<>(), LocalDateTime.now());
        TheUser theUser = new TheUser();
        List<LineCandidate> foundLineCandidates = new ArrayList<>();
        foundLineCandidates.add(lineCandidate);
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findAll())
            .thenReturn(foundLineCandidates);

        Assertions.assertDoesNotThrow(() -> mylineService.getMyLineBoard("normalSession"));
    }

    @Test
    void getMyLineBoardTestEmpty() {
        TheUser theUser = new TheUser();
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findAll())
            .thenReturn(new ArrayList<>());

        Assertions.assertDoesNotThrow(() -> mylineService.getMyLineBoard("normalSession"));
    }

    @Test
    void likeMyLineTestNormal() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        List<LineCandidate> dislikedCandidates = new ArrayList<>();
        dislikedCandidates.add(lineCandidate);
        theUser.setDislikedCandidates(dislikedCandidates);
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.likeMyLine(requestDto, "normalSession"));
    }

    @Test
    void likeMyLineTestNotDisliked() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.likeMyLine(requestDto, "normalSession"));
    }

    @Test
    void unlikeMyLineNormal() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        List<LineCandidate> likedCandidates = new ArrayList<>();
        likedCandidates.add(lineCandidate);
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(likedCandidates);
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.unlikeMyLine(requestDto, "normalSession"));
    }

    @Test
    void unlikeMyLineNotLiked() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.unlikeMyLine(requestDto, "normalSession"));
    }


    @Test
    void dislikeMyLineTestNormal() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        List<LineCandidate> likedCandidates = new ArrayList<>();
        likedCandidates.add(lineCandidate);
        theUser.setLikedCandidates(likedCandidates);
        theUser.setDislikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.dislikeMyLine(requestDto, "normalSession"));
    }

    @Test
    void dislikeMyLineTestNotDisliked() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.dislikeMyLine(requestDto, "normalSession"));
    }

    @Test
    void undislikeMyLineNormal() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        List<LineCandidate> dislikedCandidates = new ArrayList<>();
        dislikedCandidates.add(lineCandidate);
        theUser.setDislikedCandidates(dislikedCandidates);
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.undislikeMyLine(requestDto, "normalSession"));
    }

    @Test
    void undislikeMyLineNotLiked() {
        LineCandidate lineCandidate = LineCandidate.create("content", Movie.create("title", null, null, null), null, null);
        TheUser theUser = new TheUser();
        theUser.setDislikedCandidates(new ArrayList<>());
        theUser.setLikedCandidates(new ArrayList<>());
        Mockito.when(sessionRepository.findUserByContent("normalSession"))
            .thenReturn(Optional.of(theUser));
        Mockito.when(lineCandidateRepository.findById(1L))
            .thenReturn(Optional.of(lineCandidate));

        LineCandidateIdDto requestDto = new LineCandidateIdDto(1L);

        Assertions.assertDoesNotThrow(() -> mylineService.undislikeMyLine(requestDto, "normalSession"));
    }
}
