package com.group2.milestone2.api.game.service;

import com.group2.milestone2.api.game.dto.ActorFromLineDto;
import com.group2.milestone2.api.game.dto.ActorFromLineGameResponse;
import com.group2.milestone2.api.game.dto.TitleFromLineGameResponse;
import com.group2.milestone2.api.game.dto.TitleFromListDto;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final LineQuoteRepository lineQuoteRepository;


    public TitleFromLineGameResponse getTitleFromLineData(Long count) {
        List<Integer> randomNumberList = getRandomNumberList(lineQuoteRepository.count(), Math.toIntExact(count));

        return new TitleFromLineGameResponse(
            randomNumberList.stream()
                .map(n -> {
                    LineQuote lineQuote = lineQuoteRepository.findById(Long.valueOf(n)).orElseThrow();
                    return new TitleFromListDto(
                        lineQuote.getMovie().getTitle(),
                        lineQuote.getContent()
                    );
                }).toList()
        );
    }

    public ActorFromLineGameResponse getActorFromLineData(Long count) {
        List<Integer> randomNumberList = getRandomNumberList(lineQuoteRepository.count(), Math.toIntExact(count));

        return new ActorFromLineGameResponse(
            randomNumberList.stream()
                .map(n -> {
                    LineQuote lineQuote = lineQuoteRepository.findById(Long.valueOf(n)).orElseThrow();
                    return new ActorFromLineDto(
                        lineQuote.getActor(),
                        lineQuote.getContent()
                    );
                }).toList()
        );
    }

    public static List<Integer> getRandomNumberList(Long totalCount, Integer count) {
        long n = totalCount;
        int m = count;
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        return list.subList(0, (int) m);
    }
}
