package com.group2.milestone2.domain.line.domain;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Getter;
@Getter
@Entity
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineTag> lineTagList;

    @Column(length = 255)
    private String content;

    @ManyToMany
    private List<User> favoriteUsers;

}
