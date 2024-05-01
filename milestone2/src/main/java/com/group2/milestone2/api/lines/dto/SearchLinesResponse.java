package com.group2.milestone2.api.lines.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchLinesResponse {

    private List<LineDto> lines;
}
