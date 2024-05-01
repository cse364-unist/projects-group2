package com.group2.milestone2.api.lines.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineDto {

    private Long id;

    private String actor;

    private String line;

    private String movie;

    private List<String> tag;

    private Long favoriteCount;

    private Boolean isFavorite;
}
