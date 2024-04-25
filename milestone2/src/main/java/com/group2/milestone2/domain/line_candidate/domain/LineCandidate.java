package com.group2.milestone2.domain.line_candidate.domain;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.user.domain.User;
import jakarta.persistence.Entity;
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

    @ManyToMany
    private List<LineTag> lineTagList;

    @ManyToMany
    private List<User> likedUser;

    @ManyToMany
    private List<User> dislikedUser;
}
