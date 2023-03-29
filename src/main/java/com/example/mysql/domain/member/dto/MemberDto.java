package com.example.mysql.domain.member.dto;

import com.example.mysql.domain.member.entity.Member;

import java.time.LocalDate;

public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {

    public static MemberDto of(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getBirthDay());
    }
}
