package com.weed.community.service;

import com.weed.community.domain.Comments;
import com.weed.community.domain.Member;
import com.weed.community.domain.Posts;
import com.weed.community.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentsService {
    private final CommentsRepository commentsRepository;

    // 댓글 작성
    public Comments createComments(Member member, Posts posts, String content) {
        Comments comments = new Comments(member, posts, content, LocalDateTime.now());
        return commentsRepository.save(comments);
    }
    // 댓글 조회
    public List<Comments> findComments() {
        return commentsRepository.findAll();
    }
    // 글 아이디로 댓글 조회
    public List<Comments> findCommentsByPostId(Long postId) {
        return commentsRepository.findByPostsId(postId);
    }
    // 댓글 수정
    public void updateComments(Long commentsId, String updateContent) {
        Comments comments = commentsRepository.findById(commentsId).orElseThrow();
        comments.setContent(updateContent);
    }
    // 댓글 삭제
    public void deleteComments(Long commentsId) {
        commentsRepository.deleteById(commentsId);
    }
}
