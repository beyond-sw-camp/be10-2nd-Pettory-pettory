package com.pettory.mainserver.board.command.application.service;

import com.pettory.mainserver.board.command.application.dto.CommentRequest;
import com.pettory.mainserver.board.command.domain.aggregate.Comment;
import com.pettory.mainserver.board.command.domain.service.BoardCommentDomainService;
import com.pettory.mainserver.board.command.infrastructure.repository.JPACommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentInsertService {

    private final BoardCommentDomainService boardCommentDomainService;
    private final JPACommentRepository jpaCommentRepository;

    // 댓글 작성
    @Transactional
    public int createComment(CommentRequest commentRequest) {

        // Comment 도메인 실행 후 entity 반환
        Comment newComment = boardCommentDomainService.createComment(commentRequest);

        // save 로직 실행
        Comment savedComment = jpaCommentRepository.save(newComment);

        // 등록된 번호 반환
        return savedComment.getCommentNum();
    }

}
