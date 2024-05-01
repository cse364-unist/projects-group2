package com.group2.milestone2.api.my_line.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyLineBoardResponse {

    List<LineCandidateDto> lineCandidateDtoList;
}
