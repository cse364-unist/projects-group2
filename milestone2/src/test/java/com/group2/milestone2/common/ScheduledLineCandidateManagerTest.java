package com.group2.milestone2.common;

import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import com.group2.milestone2.domain.line_candidate.repository.LineCandidateRepository;
import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_quote.repository.LineQuoteRepository;
import com.group2.milestone2.domain.user.domain.TheUser;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.AssertionInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootTest
public class ScheduledLineCandidateManagerTest {

    @Mock
    private LineCandidateRepository lineCandidateRepository;
    @Mock
    private LineQuoteRepository lineQuoteRepository;
    @InjectMocks
    private ScheduledLineCandidateManager scheduledLineCandidateManager;

    @Test
    public void manageLineCandidateNormal() {
        List<TheUser> likedUsers = new ArrayList<>();
        Collections.addAll(likedUsers, new TheUser(), new TheUser(), new TheUser(), new TheUser(), new TheUser(), new TheUser(),
            new TheUser(), new TheUser(), new TheUser(), new TheUser(), new TheUser());
        LineCandidate lineCandidate = LineCandidate.create(null, null, null, null
            , LocalDateTime.now().minusHours(1L), likedUsers, new ArrayList<>());
        List<LineCandidate> lineCandidateList = new ArrayList<>();
        lineCandidateList.add(lineCandidate);
        Mockito.when(lineCandidateRepository.findOlderThanNow())
            .thenReturn(lineCandidateList);

        Assertions.assertDoesNotThrow(() -> scheduledLineCandidateManager.manageLineCandidate());

    }

    @Test
    public void manageLineCandidateNotPass() {
        List<TheUser> likedUsers = new ArrayList<>();
        LineCandidate lineCandidate = LineCandidate.create(null, null, null, null
            , LocalDateTime.now().minusHours(1L), likedUsers, new ArrayList<>());
        List<LineCandidate> lineCandidateList = new ArrayList<>();
        lineCandidateList.add(lineCandidate);
        Mockito.when(lineCandidateRepository.findOlderThanNow())
            .thenReturn(lineCandidateList);

        Assertions.assertDoesNotThrow(() -> scheduledLineCandidateManager.manageLineCandidate());

    }
}
