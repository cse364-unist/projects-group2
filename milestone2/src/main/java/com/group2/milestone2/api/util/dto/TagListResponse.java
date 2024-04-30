package com.group2.milestone2.api.util.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TagListResponse {
    List<TagDto> tagDtoList;
}
