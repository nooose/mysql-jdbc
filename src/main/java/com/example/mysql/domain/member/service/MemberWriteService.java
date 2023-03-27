package com.example.mysql.domain.member.service;

import com.example.mysql.domain.member.dto.RegisterMemberCommand;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;

    public Member create(RegisterMemberCommand command) {
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthDay(command.birthday())
                .build();

        return memberRepository.save(member);
    }


}
