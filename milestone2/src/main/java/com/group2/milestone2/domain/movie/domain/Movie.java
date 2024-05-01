package com.group2.milestone2.domain.movie.domain;

import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 500)
    private String posterUrl;

    private LocalDate releaseDate;

    @Column(length = 2048)
    private String abstraction;

    @OneToMany(fetch = FetchType.LAZY)
    private List<LineQuote> lines;

    @OneToMany(fetch = FetchType.LAZY)
    private List<LineCandidate> lineCandidates;

    public static Movie create(
        String title,
        String posterUrl,
        LocalDate releaseDate,
        String abstraction
    ){
        Movie movie = new Movie();
        movie.title = title;
        movie.posterUrl = posterUrl;
        movie.releaseDate = releaseDate;
        movie.abstraction = abstraction;
        return movie;
    }

    public void setLines(List<LineQuote> lines){
        this.lines = lines;
    }

    public void setLineCandidates(List<LineCandidate> lineCandidates){
        this.lineCandidates = lineCandidates;
    }
}
