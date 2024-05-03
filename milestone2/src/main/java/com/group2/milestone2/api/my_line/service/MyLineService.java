package com.group2.milestone2.api.my_line.service;

import com.group2.milestone2.api.my_line.dto.LineCandidateDto;
import com.group2.milestone2.api.my_line.dto.LineCandidateIdDto;
import com.group2.milestone2.api.my_line.dto.MyLineBoardResponse;
import com.group2.milestone2.api.my_line.dto.UploadMyLineRequestDto;
import com.group2.milestone2.common.AuthHandler;
import com.group2.milestone2.common.DateHandler;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.repository.LineCandidateRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.line_tag.repository.LineTagRepository;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.movie.repository.MovieRepository;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyLineService {

    private final LineTagRepository lineTagRepository;
    private final MovieRepository movieRepository;
    private final LineCandidateRepository lineCandidateRepository;
    private final SessionRepository sessionRepository;


    @Transactional
    public void uploadMyLine(UploadMyLineRequestDto requestDto) {

        Movie movie = movieRepository.findByTitle(requestDto.getTitle())
            .orElse(Movie.create(requestDto.getTitle(), null, null, null));

        List<LineTag> lineTagList = requestDto.getTags().stream()
            .map(tagString -> lineTagRepository.findById(tagString).orElse(LineTag.create(tagString))).toList();

        LineCandidate lineCandidate = LineCandidate.create(
            requestDto.getLine(),
            movie,
            requestDto.getActor(),
            lineTagList,
            LocalDateTime.now().plusDays(1)
        );
        lineCandidateRepository.save(lineCandidate);
    }

    public MyLineBoardResponse getMyLineBoard(String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow();

        List<LineCandidate> lineCandidateList = lineCandidateRepository.findAll();

        return new MyLineBoardResponse(
            lineCandidateList.stream()
                .map(lineCandidate -> new LineCandidateDto(
                    lineCandidate.getId(),
                    lineCandidate.getContent(),
                    lineCandidate.getMovie().getTitle(),
                    lineCandidate.getLineTagList().stream()
                        .map(LineTag::getContent).toList(),
                    DateHandler.locatDateTimeToString(lineCandidate.getExpireAt()),
                    (long) lineCandidate.getLikedUser().size(),
                    (long) lineCandidate.getDislikedUser().size(),
                    lineCandidate.getLikedUser().contains(user),
                    lineCandidate.getDislikedUser().contains(user)
                ))
                .toList()
        );

    }

    @Transactional
    public void likeMyLine(LineCandidateIdDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow();

        LineCandidate lineCandidateById = lineCandidateRepository.findById(requestDto.getId()).orElseThrow();

        if(user.getDislikedCandidates().contains(lineCandidateById)){
            List<LineCandidate> dislikedCandidates = user.getDislikedCandidates();
            dislikedCandidates.remove(lineCandidateById);
            user.setDislikedCandidates(dislikedCandidates);
        }

        List<LineCandidate> likedCandidates = user.getLikedCandidates();
        likedCandidates.add(lineCandidateById);
        user.setLikedCandidates(likedCandidates);
    }
    @Transactional
    public void unlikeMyLine(LineCandidateIdDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow();

        LineCandidate lineCandidateById = lineCandidateRepository.findById(requestDto.getId()).orElseThrow();

        List<LineCandidate> likedCandidates = user.getLikedCandidates();
        likedCandidates.remove(lineCandidateById);
        user.setLikedCandidates(likedCandidates);

    }

    @Transactional
    public void dislikeMyLine(LineCandidateIdDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow();

        LineCandidate lineCandidateById = lineCandidateRepository.findById(requestDto.getId()).orElseThrow();

        if(user.getLikedCandidates().contains(lineCandidateById)){
            List<LineCandidate> likedCandidates = user.getLikedCandidates();
            likedCandidates.remove(lineCandidateById);
            user.setLikedCandidates(likedCandidates);
        }

        List<LineCandidate> dislikedCandidates = user.getDislikedCandidates();
        dislikedCandidates.add(lineCandidateById);
        user.setDislikedCandidates(dislikedCandidates);

    }

    @Transactional
    public void undislikeMyLine(LineCandidateIdDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow();

        LineCandidate lineCandidateById = lineCandidateRepository.findById(requestDto.getId()).orElseThrow();

        List<LineCandidate> dislikedCandidates = user.getDislikedCandidates();
        dislikedCandidates.remove(lineCandidateById);
        user.setDislikedCandidates(dislikedCandidates);

    }
}
