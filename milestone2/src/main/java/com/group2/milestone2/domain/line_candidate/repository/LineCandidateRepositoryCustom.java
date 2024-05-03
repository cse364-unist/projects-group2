package com.group2.milestone2.domain.line_candidate.repository;

import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import java.util.List;

public interface LineCandidateRepositoryCustom {

    List<LineCandidate> findOlderThanNow();
}
