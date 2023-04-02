package com.example.mysql.application.usecase;

import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.service.FollowReadService;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.entity.Timeline;
import com.example.mysql.domain.post.service.PostReadService;
import com.example.mysql.domain.post.service.TimelineReadService;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest request) {
        var followings = followReadService.getFollowing(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, request);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        var pagedTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimelines.body().stream().map(Timeline::getPostId).toList();
        var posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
    }
}
