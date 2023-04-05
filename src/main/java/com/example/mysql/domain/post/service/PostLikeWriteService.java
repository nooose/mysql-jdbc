package com.example.mysql.domain.post.service;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.entity.PostLike;
import com.example.mysql.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        PostLike postLike = PostLike.builder()
                .memberId(memberDto.id())
                .postId(post.getId())
                .build();
        return postLikeRepository.save(postLike).getPostId();
    }
}
