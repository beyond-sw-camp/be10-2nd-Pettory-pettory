package com.pettory.mainserver.board.command.application.service;

import com.pettory.mainserver.board.command.application.dto.PostUpdateRequest;
import com.pettory.mainserver.board.command.domain.aggregate.Post;
import com.pettory.mainserver.board.command.domain.service.BoardPostDomainService;
import com.pettory.mainserver.board.command.infrastructure.repository.JPAPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPostUpdateService {

    private final BoardPostDomainService boardPostDomainService;
    private final JPAPostRepository postRepository;

    // 글 수정
    @Transactional
    public void updatePost(int postNum, PostUpdateRequest postUpdateRequest) {
        // 1. 기존 게시글 조회
        Post post = postRepository.findById((long) postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 2. 도메인 서비스로 게시글 수정 로직 위임
        boardPostDomainService.updatePost(post, postUpdateRequest.getPostTitle(), postUpdateRequest.getPostContent());

        // 3. 변경된 게시글 저장
        postRepository.save(post);
    }


}
