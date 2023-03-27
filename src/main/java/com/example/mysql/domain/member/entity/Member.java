package com.example.mysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private static final int NAME_MAX_LENGTH = 10;

    private final Long id;
    private String nickname;
    private final String email;
    private final LocalDate birthDay;
    private final LocalDateTime createdAt;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthDay, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthDay = Objects.requireNonNull(birthDay);
        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = Objects.requireNonNullElse(createdAt, LocalDateTime.now());
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
