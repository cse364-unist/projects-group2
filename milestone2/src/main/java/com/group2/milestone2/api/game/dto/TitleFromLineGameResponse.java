package com.group2.milestone2.api.game.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TitleFromLineGameResponse {
    List<TitleFromListDto> data;
}
