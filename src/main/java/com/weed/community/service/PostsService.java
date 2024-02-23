package com.weed.community.service;

import com.weed.community.domain.Posts;
import com.weed.community.dto.PostsDto;
import com.weed.community.dto.PostsUpdateRequest;
import com.weed.community.repository.MemberRepository;
import com.weed.community.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    // 게시물 작성
    public Posts createPosts(PostsDto postsDto) {
        Posts posts = new Posts(postsDto.getMember(), postsDto.getTitle(), postsDto.getContent(), LocalDateTime.now());
        return postsRepository.save(posts);
    }
    // 게시물 조회
    public Optional<Posts> findPostsOne(Long postsId) {
        return postsRepository.findById(postsId);
    }
    // 게시물 전체 조회
    public List<Posts> findPosts() {
        return postsRepository.findAll();
    }
    // 게시물 수정
    public void updatePosts(Long postId, PostsUpdateRequest updateRequest) {
        Posts posts = postsRepository.findById(postId).orElseThrow();
        posts.setTitle(updateRequest.getTitle());
        posts.setContent(updateRequest.getContent());
        posts.setCreateDate(LocalDateTime.now());
    }
    // 게시물 삭제
    public void deletePosts(Long postId) {
        postsRepository.deleteById(postId);
    }
}
