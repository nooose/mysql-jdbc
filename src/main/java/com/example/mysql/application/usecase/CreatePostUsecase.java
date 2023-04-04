package com.example.mysql.application.usecase;

import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.service.FollowReadService;
import com.example.mysql.domain.post.dto.PostCommand;
import com.example.mysql.domain.post.service.PostWriteService;
import com.example.mysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    @Transactional
    public Long execute(PostCommand postCommand) {
        var postId = postWriteService.create(postCommand);

        var followerMemberIds = followReadService.getFollowers(postCommand.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();
        timelineWriteService.deliveryToTimeline(postId, followerMemberIds);
        return postId;
    }
}
