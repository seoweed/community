package com.weed.community.dto;

import lombok.Data;

@Data
public class PostsUpdateRequest {
    private String title;
    private String content;
}
