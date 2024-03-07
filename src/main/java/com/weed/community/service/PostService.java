package com.weed.community.service;

import com.weed.community.domain.Member;
import com.weed.community.domain.Posts;
import com.weed.community.dto.PostRequest;
import com.weed.community.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsRepository postsRepository;

    // 글 저장
    @Transactional
    public Long savePost(final PostRequest postRequest, Member LoginMember) {
        Posts post = new Posts(LoginMember, postRequest.getTitle(), postRequest.getContent(), LocalDateTime.now());
        postsRepository.save(post);
        return post.getId();
    }

}
