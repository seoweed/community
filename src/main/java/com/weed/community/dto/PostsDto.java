package com.weed.community.dto;

import com.weed.community.domain.Member;
import lombok.Data;

@Data
public class PostsDto {
    private Member member;
    private String title;
    private String content;
}
