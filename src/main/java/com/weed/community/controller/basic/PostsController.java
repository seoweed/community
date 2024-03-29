package com.weed.community.controller.basic;

import com.weed.community.domain.Comments;
import com.weed.community.domain.Member;
import com.weed.community.domain.Posts;
import com.weed.community.dto.PostRequest;
import com.weed.community.dto.PostsDto;
import com.weed.community.service.CommentsService;
import com.weed.community.service.PostService;
import com.weed.community.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final PostService postService;

    // 글 작성
    @GetMapping("/write")
    public String postRegister(@SessionAttribute(name = "loginMember", required = false) Member loginMember) {
        if (loginMember == null) {
            //TODO 로그인 필요 메세지 추가
            return "home";
        }
        return "write";
    }
    @PostMapping("/write")
    public String postsRegister(PostsDto postsDto, HttpServletRequest request) {
        Member loginMember = (Member) request.getSession(false).getAttribute("loginMember");
        postsService.createPosts(postsDto, loginMember);
        return "redirect:/";
    }
    // 글 작성 (tui-editor 사용)
    @PostMapping("/write-tui")
    public String writeTui(
            @RequestBody PostRequest postRequest,
            @SessionAttribute(name = "loginMember", required = false) Member loginMember
    ) {
        postService.savePost(postRequest, loginMember);
        return "redirect:/posts";
    }
    // 글 목록 조회
    @GetMapping("/posts")
    public String readPosts(
            Model model,
            @SessionAttribute(name = "loginMember", required = false) Member loginMember
    ) {
        List<Posts> posts = postsService.findPosts();
        model.addAttribute("posts", posts);
        if (loginMember == null) {
            return "posts";
        }
        return "loginPosts";
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
    /**
     * 글 삭제시 단방향 연관관계인 댓글이 참조되어 삭제가 되지 않았음
     * 해결 -> 글과 댓글을 양방향 매핑으로 구성하고 cascade = CascadeType.ALL 옵션 적용
     * 글을 삭제 할 때 글에 달렸던 댓글도 모두 삭제되도록 구현
     */
    // 글 삭제
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable Long postId,
                             @SessionAttribute(name = "loginMember", required = false) Member loginMember) {
        Posts posts = postsService.findPostsOne(postId).orElseThrow();
        if (posts.getMember().getId().equals(loginMember.getId())) {
            postsService.deletePostsById(postId);
            return "redirect:/posts";
        }
        //TODO error 추가
        return "redirect:/posts";
    }
}
