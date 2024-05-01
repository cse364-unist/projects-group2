package com.group2.milestone2.api.game.controller;

import com.group2.milestone2.api.game.dto.ActorFromLineGameResponse;
import com.group2.milestone2.api.game.dto.TitleFromLineGameResponse;
import com.group2.milestone2.api.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/titlefromline/problems")
    public ResponseEntity<TitleFromLineGameResponse> getTitleFromLineData(@RequestParam Long count){
        return ResponseEntity.ok(gameService.getTitleFromLineData(count));
    }

    @GetMapping("/actorfromline/problems")
    public ResponseEntity<ActorFromLineGameResponse> getActorFromLineData(@RequestParam Long count){
        return ResponseEntity.ok(gameService.getActorFromLineData(count));
    }
}
