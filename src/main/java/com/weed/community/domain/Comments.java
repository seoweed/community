package com.weed.community.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments {
    @Id
    @GeneratedValue
    @Column(name = "comments_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "posts_id")
    private Posts posts;
    private String content;
    private LocalDateTime createDate;

    public Comments(Member member, Posts posts, String content, LocalDateTime createDate) {
        this.member = member;
        this.posts = posts;
        this.content = content;
        this.createDate = createDate;
    }
}
