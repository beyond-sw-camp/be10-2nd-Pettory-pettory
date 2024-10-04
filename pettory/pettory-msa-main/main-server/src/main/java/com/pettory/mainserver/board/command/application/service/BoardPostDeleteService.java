package com.pettory.mainserver.board.command.application.service;

import com.pettory.mainserver.board.command.domain.aggregate.Post;
import com.pettory.mainserver.board.command.domain.service.BoardPostDomainService;
import com.pettory.mainserver.board.command.infrastructure.repository.JPAPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPostDeleteService {

    private final BoardPostDomainService boardPostDomainService;
    private final JPAPostRepository postRepository;

    // 게시글 삭제
    // 게시글 삭제 요청 처리
    @Transactional
    public void deletePost(int postNum) {
        // 1. 게시글을 조회
        Post post = postRepository.findById((long) postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 2. 도메인 서비스에 게시글 삭제 요청
        boardPostDomainService.deletePost(post);

        // 3. 변경된 게시글 저장
        postRepository.save(post);
    }



}
