package com.group2.milestone2.api.util.controller;

import com.group2.milestone2.api.util.dto.AddFavoriteRequestDto;
import com.group2.milestone2.api.util.dto.TagListResponse;
import com.group2.milestone2.api.util.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {

    private final UtilService utilService;

    @GetMapping("/taglist")
    public ResponseEntity<TagListResponse> getTagList(){
        return ResponseEntity.ok(utilService.getTagList());
    }

    @PostMapping("/addfavorite")
    public void addFavorite(@RequestBody AddFavoriteRequestDto requestDto, @CookieValue String session){
        utilService.addFavorite(requestDto, session);
    }

    @PostMapping("/removefavorite")
    public void removeFavorite(@RequestBody AddFavoriteRequestDto requestDto, @CookieValue String session){
        utilService.removeFavorite(requestDto, session);
    }
}
