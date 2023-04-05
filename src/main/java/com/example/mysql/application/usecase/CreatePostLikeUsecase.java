package com.example.mysql.application.usecase;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.service.MemberReadService;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.service.PostLikeWriteService;
import com.example.mysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostLikeUsecase {
    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        Post post = postReadService.getPost(postId);
        MemberDto member = memberReadService.getMember(memberId);
        postLikeWriteService.create(post, member);
    }
}
