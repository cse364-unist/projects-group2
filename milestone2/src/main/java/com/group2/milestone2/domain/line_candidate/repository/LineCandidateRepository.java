package com.group2.milestone2.domain.line_candidate.repository;

import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineCandidateRepository extends JpaRepository<LineCandidate, Long>, LineCandidateRepositoryCustom {

}
