package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.application.dto.CommentUpdateRequest;
import com.pettory.pettory.board.command.domain.aggregate.Comment;
import com.pettory.pettory.board.command.domain.service.BoardCommentDomainService;
import com.pettory.pettory.board.command.domain.service.BoardPostDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPACommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentUpdateService {

    private final BoardCommentDomainService boardCommentDomainService;
    private final JPACommentRepository jpacommentRepository;

    // 댓글 수정
    @Transactional
    public void updateComment(int commentNum, CommentUpdateRequest commentUpdateRequest) {

        // 1. 기존 댓글 조회
        Comment comment = jpacommentRepository.findById((long) commentNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 2. 도메인 서비스로 댓글 수정 로직 위임
        boardCommentDomainService.updateComment(comment, commentUpdateRequest.getCommentContent());

        // 3. 변경된 게시글 저장
        jpacommentRepository.save(comment);


    }
}


