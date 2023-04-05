package com.example.mysql.domain.post.dto;

import com.example.mysql.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String contents,
        LocalDateTime createdAt,
        Long likeCount
) {

    public static PostDto of(Post post, Long count) {
        return new PostDto(
                post.getId(),
                post.getContents(),
                post.getCreatedAt(),
                count);
    }
}
