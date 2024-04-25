package com.group2.milestone2.domain.movie.domain;

import com.group2.milestone2.domain.line.domain.Line;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Movie {

    @Id
    private Long id;

    private String title;

    private LocalDate releaseDate;

    @Column(length = 500)
    private String abstraction;

    @OneToMany
    private List<Line> lines;

    @OneToMany
    private List<LineCandidate> lineCandidates;
}
