package com.group2.milestone2.domain.user.domain;

import com.group2.milestone2.domain.line.domain.Line;
import com.group2.milestone2.domain.line_candidate.domain.LineCandidate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id
    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String password;

    @ManyToMany
    private List<Line> favoriteLines;

    @ManyToMany
    private List<LineCandidate> likedCandidates;

    public static User create(String userId, String password){
        User user = new User();
        user.userId = userId;
        user.password = password
        return user;
    }

}
