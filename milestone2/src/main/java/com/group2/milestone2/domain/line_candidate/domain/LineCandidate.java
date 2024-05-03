package com.group2.milestone2.domain.line_candidate.domain;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.user.domain.TheUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class LineCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Movie movie;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LineTag> lineTagList;

    private LocalDateTime expireAt;

    public static LineCandidate create(
        String content,
        Movie movie,
        List<LineTag> lineTagList,
        LocalDateTime expireAt
    ) {
        LineCandidate lineCandidate = new LineCandidate();
        lineCandidate.content = content;
        lineCandidate.movie = movie;
        lineCandidate.lineTagList = lineTagList;
        lineCandidate.expireAt = expireAt;
        lineCandidate.likedUser = new ArrayList<>();
        lineCandidate.dislikedUser = new ArrayList<>();
        return lineCandidate;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedCandidates")
    private List<TheUser> likedUser;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dislikedCandidates")
    private List<TheUser> dislikedUser;
}
