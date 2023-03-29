package com.example.mysql.domain.member.entity;

import com.example.mysql.util.MemberFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    void changeName() {
        Member member = MemberFixtureFactory.create();
        var expected = "noose";

        member.changeNickname(expected);

        assertThat(member.getNickname()).isEqualTo(expected);
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    void nicknameMaxLength() {
        Member member = MemberFixtureFactory.create();
        var overMaxLengthName = "noosenoose1";

        assertThatThrownBy(() -> member.changeNickname(overMaxLengthName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
