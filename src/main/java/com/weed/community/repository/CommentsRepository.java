package com.weed.community.repository;

import com.weed.community.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostsId(Long postsId);
}
