package com.group2.milestone2.domain.user.domain;

import com.group2.milestone2.domain.line_quote.domain.LineQuote;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class TheUser {

    @Id
    @NotNull
    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineQuote> favoriteLines;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineCandidate> likedCandidates;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LineCandidate> dislikedCandidates;

    public static TheUser create(String userId, String password){
        TheUser user = new TheUser();
        user.userId = userId;
        user.password = password;
        return user;
    }

    public void setFavoriteLines(List<LineQuote> favoriteLines){
        this.favoriteLines = favoriteLines;
    }

    public void setLikedCandidates(List<LineCandidate> lineCandidates){
        this.likedCandidates = lineCandidates;
    }

    public void setDislikedCandidates(List<LineCandidate> lineCandidates){
        this.dislikedCandidates = lineCandidates;
    }

}
