package com.group2.milestone2.api.my_line.controller;

import com.group2.milestone2.api.my_line.dto.LineCandidateIdDto;
import com.group2.milestone2.api.my_line.dto.MyLineBoardResponse;
import com.group2.milestone2.api.my_line.dto.UploadMyLineRequestDto;
import com.group2.milestone2.api.my_line.service.MyLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myline")
@RequiredArgsConstructor
public class MyLineController {

    private final MyLineService myLineService;

    @PostMapping("/upload")
    public void uploadMyLine(@RequestBody UploadMyLineRequestDto requestDto){
        myLineService.uploadMyLine(requestDto);
    }

    @GetMapping("/board")
    public ResponseEntity<MyLineBoardResponse> getMyLineBoard(@CookieValue String session){
        return ResponseEntity.ok(myLineService.getMyLineBoard(session));
    }

    @PostMapping("/like")
    public void likeMyLine(@RequestBody LineCandidateIdDto requestDto, @CookieValue String session){
        myLineService.likeMyLine(requestDto, session);
    }

    @PostMapping("/unlike")
    public void unlikeMyLine(@RequestBody LineCandidateIdDto requestDto, @CookieValue String session){
        myLineService.unlikeMyLine(requestDto, session);
    }

    @PostMapping("/dislike")
    public void dislikeMyLine(@RequestBody LineCandidateIdDto requestDto, @CookieValue String session){
        myLineService.dislikeMyLine(requestDto, session);
    }

    @PostMapping("/undislike")
    public void undislikeMyLine(@RequestBody LineCandidateIdDto requestDto, @CookieValue String session){
        myLineService.undislikeMyLine(requestDto, session);
    }
}
