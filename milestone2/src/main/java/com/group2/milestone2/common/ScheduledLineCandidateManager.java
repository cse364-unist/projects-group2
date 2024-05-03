package com.group2.milestone2.common;

import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.repository.LineCandidateRepository;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledLineCandidateManager {

    private final LineCandidateRepository lineCandidateRepository;
    private final LineQuoteRepository lineQuoteRepository;

    @Scheduled(cron = "0 * * * * *")
    public void manageLineCandidate() {
        System.out.println("managing line candidates");
        List<LineCandidate> olderThanNow = lineCandidateRepository.findOlderThanNow();
        for (LineCandidate lineCandidate: olderThanNow
        ) {
            int score = lineCandidate.getLikedUser().size() - lineCandidate.getDislikedUser().size();
            if (score>10){
                lineQuoteRepository.save(LineQuote.fromLineCandidate(lineCandidate));
            }
            lineCandidateRepository.delete(lineCandidate);
        }
    }
}
