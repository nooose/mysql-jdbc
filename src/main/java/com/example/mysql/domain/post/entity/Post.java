package com.example.mysql.domain.post.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Post {
    private final Long id;
    private final Long memberId;
    private final String contents;
    private final LocalDate createdDate;
    private Long likeCount;
    private final LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, Long likeCount, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = Objects.requireNonNullElse(createdDate, LocalDate.now());
        this.likeCount = Objects.requireNonNullElse(likeCount, 0L);
        this.createdAt = Objects.requireNonNullElse(createdAt, LocalDateTime.now());
    }

    public void incrementLikeCount() {
        this.likeCount += 1;
    }
}
