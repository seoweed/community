package com.weed.community.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts {
    @Id
    @GeneratedValue
    @Column(name = "posts_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    @Column(length = 50000)
    private String content;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> comments;

    public Posts(Member member, String title, String content, LocalDateTime createDate) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }
}
