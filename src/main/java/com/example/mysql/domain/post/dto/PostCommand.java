package com.example.mysql.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
