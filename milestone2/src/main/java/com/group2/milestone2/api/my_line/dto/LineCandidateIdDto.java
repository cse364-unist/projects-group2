package com.group2.milestone2.api.my_line.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LineCandidateIdDto {
    private Long id;

    @JsonCreator
    public LineCandidateIdDto(@JsonProperty("id") Long id) {
        this.id = id;
    }
}
