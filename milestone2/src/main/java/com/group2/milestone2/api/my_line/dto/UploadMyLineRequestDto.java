package com.group2.milestone2.api.my_line.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadMyLineRequestDto {

    String line;

    String title;

    String actor;

    List<String> tags;
}
