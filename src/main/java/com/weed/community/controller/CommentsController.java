package com.weed.community.controller;

import com.weed.community.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    // 댓글 작성
    // todo member, post 값 추가
    @PostMapping("/comment")
    public String addComments(String content) {
        commentsService.createComments(null, null, content);
        return "redirect:/";
    }
}
