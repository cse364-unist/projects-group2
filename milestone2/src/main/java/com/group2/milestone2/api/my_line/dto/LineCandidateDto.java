package com.group2.milestone2.api.my_line.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineCandidateDto {
    Long id;

    String line;

    String movie;

    List<String> tags;

    String expireAt;

    Long like;

    Long dislike;

    Boolean isLike;

    Boolean isDislike;
}
