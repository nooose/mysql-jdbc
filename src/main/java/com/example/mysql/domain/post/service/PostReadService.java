package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.dto.DailyPostCount;
import com.example.mysql.domain.post.dto.DailyPostCountRequest;
import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.repository.PostLikeRepository;
import com.example.mysql.domain.post.repository.PostRepository;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }

    public Page<PostDto> getPosts(Long memberId, Pageable pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest)
                .map(post -> PostDto.of(post, postLikeRepository.count(post.getId())));
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        var nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private long getNextKey(List<Post> posts) {
        return posts.stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }

    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
        var posts = findAllBy(memberIds, cursorRequest);
        var nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public List<Post> getPosts(List<Long> ids) {
        return postRepository.findAllByInId(ids);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId, false).orElseThrow();
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }

        return postRepository.findAllByInMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }

    private List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberIds, cursorRequest.size());
        }

        return postRepository.findAllByInMemberIdAndOrderByIdDesc(memberIds, cursorRequest.size());
    }
}
