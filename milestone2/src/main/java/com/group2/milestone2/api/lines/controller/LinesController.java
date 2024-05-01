package com.group2.milestone2.api.lines.controller;

import com.group2.milestone2.api.lines.dto.SearchLinesRequestDto;
import com.group2.milestone2.api.lines.dto.SearchLinesResponse;
import com.group2.milestone2.api.lines.service.LinesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lines")
public class LinesController {

    private final LinesService linesService;

    @GetMapping("/search")
    public ResponseEntity<SearchLinesResponse> searchLines( @CookieValue String session, @ModelAttribute SearchLinesRequestDto requestDto) {
        return ResponseEntity.ok(linesService.searchLines(requestDto, session));
    }
}
