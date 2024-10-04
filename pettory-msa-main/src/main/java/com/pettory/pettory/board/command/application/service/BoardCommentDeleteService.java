package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.domain.aggregate.Comment;
import com.pettory.pettory.board.command.domain.service.BoardCommentDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPACommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentDeleteService {

    private final BoardCommentDomainService boardCommentDomainService;
    private final JPACommentRepository jpacommentRepository;

    // 댓글 삭제
    @Transactional
    public void deleteComment(int commentNum) {
        // 1. 댓글을 조회
        Comment comment = jpacommentRepository.findById((long) commentNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 2. 도메인 서비스에 댓글 삭제 요청
        boardCommentDomainService.deleteComment(comment);

        // 3. 변경된 게시글 저장
        jpacommentRepository.save(comment);
    }
}
