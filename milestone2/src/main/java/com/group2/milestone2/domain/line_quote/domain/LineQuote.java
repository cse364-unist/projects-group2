package com.group2.milestone2.domain.line_quote.domain;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import com.group2.milestone2.domain.movie.domain.Movie;
import com.group2.milestone2.domain.user.domain.TheUser;
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
public class LineQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String content;

    private String actor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineTag> lineTagList;


    @ManyToMany
    private List<TheUser> favoriteUsers;

    public void setFavoriteUsers(List<TheUser> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public static LineQuote create(
        String content,
        String actor,
        Movie movie,
        List<LineTag> lineTagList
    ) {
        LineQuote lineQuote = new LineQuote();
        lineQuote.content = content;
        lineQuote.actor = actor;
        lineQuote.movie = movie;
        lineQuote.lineTagList = lineTagList;
        return lineQuote;
    }

}
