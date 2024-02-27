package com.weed.community.controller;

import com.weed.community.dto.PostsDto;
import com.weed.community.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;

    // 글 작성
    @GetMapping("/write")
    public String postRegister() {
        return "write";
    }
    // todo 작성자 추가
    @PostMapping("/write")
    public String postsRegister(PostsDto postsDto) {
        postsService.createPosts(postsDto, null);
        return "redirect:/";
    }
}
