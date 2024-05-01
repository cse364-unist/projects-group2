package com.group2.milestone2.domain.line_candidate.domain;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.user.domain.TheUser;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class LineCandidate {

    @Id
    private Long id;

    private String content;
    @ManyToOne
    private Movie movie;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineTag> lineTagList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<TheUser> likedUser;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<TheUser> dislikedUser;
}
