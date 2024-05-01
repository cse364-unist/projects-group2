package com.group2.milestone2.api.util.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class AddFavoriteRequestDto {
    private Long id;

    @JsonCreator
    public AddFavoriteRequestDto(@JsonProperty("id") Long id) {
        this.id = id;
    }
}
