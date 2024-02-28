package com.weed.community.controller;

import com.weed.community.domain.Comments;
import com.weed.community.domain.Member;
import com.weed.community.domain.Posts;
import com.weed.community.dto.PostsDto;
import com.weed.community.service.CommentsService;
import com.weed.community.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    // 글 작성
    @GetMapping("/write")
    public String postRegister() {
        return "write";
    }
    @PostMapping("/write")
    public String postsRegister(PostsDto postsDto, HttpServletRequest request) {
        Member loginMember = (Member) request.getSession(false).getAttribute("loginMember");
        postsService.createPosts(postsDto, loginMember);
        return "redirect:/";
    }
    // 글 목록 조회
    @GetMapping("/posts")
    public String readPosts(Model model) {
        List<Posts> posts = postsService.findPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }
    // 단일 글 조회
    @GetMapping("/post/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        Posts posts = postsService.findPostsOne(postId).orElseThrow();
        List<Comments> comments = commentsService.findCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        model.addAttribute("post", posts);
        return "post";
    }
}
