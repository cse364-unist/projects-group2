package com.group2.milestone2.api.util.service;

import com.group2.milestone2.api.util.dto.AddFavoriteRequestDto;
import com.group2.milestone2.api.util.dto.TagDto;
import com.group2.milestone2.api.util.dto.TagListResponse;
import com.group2.milestone2.common.AuthHandler;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.line_tag.repository.LineTagRepository;
import com.group2.milestone2.domain.session.repository.SessionRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilService {

    private final LineTagRepository lineTagRepository;
    private final SessionRepository sessionRepository;
    private final LineQuoteRepository lineQuoteRepository;

    public TagListResponse getTagList() {
        List<LineTag> allTag = lineTagRepository.findAll();
        List<TagDto> tagDtoList = allTag.stream()
            .map(LineTag::getContent)
            .map(TagDto::new)
            .toList();
        return new TagListResponse(tagDtoList);
    }


    public void addFavorite(AddFavoriteRequestDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow(RuntimeException::new);

        List<LineQuote> favoriteLines = user.getFavoriteLines();
        LineQuote lineQuoteById = lineQuoteRepository.findById(requestDto.getId()).orElseThrow();
        favoriteLines.add(lineQuoteById);

        user.setFavoriteLines(favoriteLines);
    }

    public void removeFavorite(AddFavoriteRequestDto requestDto, String session) {
        AuthHandler authHandler = new AuthHandler(sessionRepository);
        TheUser user = authHandler.authorizeSession(session).orElseThrow(RuntimeException::new);

        List<LineQuote> favoriteLines = user.getFavoriteLines();
        LineQuote lineQuoteById = lineQuoteRepository.findById(requestDto.getId()).orElseThrow();
        favoriteLines.remove(lineQuoteById);

        user.setFavoriteLines(favoriteLines);
    }
}
