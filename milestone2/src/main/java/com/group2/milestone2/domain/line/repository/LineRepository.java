package com.group2.milestone2.domain.line.repository;

import com.group2.milestone2.domain.line.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

}
