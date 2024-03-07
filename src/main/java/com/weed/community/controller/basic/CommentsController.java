package com.weed.community.controller.basic;

import com.weed.community.domain.Member;
import com.weed.community.domain.Posts;
import com.weed.community.service.CommentsService;
import com.weed.community.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;
    private final PostsService postsService;

    // 댓글 작성
    @PostMapping("/comment/{postsId}")
    public String addComments(@ModelAttribute("content") String content, @PathVariable Long postsId, HttpServletRequest request) {
        Member loginMember = (Member) request.getSession(false).getAttribute("loginMember");
        Posts posts = postsService.findPostsOne(postsId).orElseThrow();
        commentsService.createComments(loginMember, posts, content);
        return "redirect:/post/" + postsId;
    }
}
