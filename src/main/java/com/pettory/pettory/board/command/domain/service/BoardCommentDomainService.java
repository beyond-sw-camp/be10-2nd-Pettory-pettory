package com.pettory.pettory.board.command.domain.service;

import com.pettory.pettory.board.command.application.dto.CommentRequest;
import com.pettory.pettory.board.command.domain.aggregate.Comment;
import com.pettory.pettory.board.command.infrastructure.repository.JPACommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentDomainService {

    private final JPACommentRepository commentRepository;

    // CommentRequest를 받아 새로운 Comment 객체 생성
    public Comment createComment(CommentRequest commentRequest) {
        return new Comment(
                commentRequest.getCommentContent(),
                commentRequest.getUserId(),
                commentRequest.getPostNum(),
                commentRequest.getRecommentNum()
        );
    }

    // 도메인 객체를 수정
    public void updateComment(Comment comment, String commentContent) {
        // 1. 댓글 내용 변경을 위한 메서드 호출
        comment.updateComment(commentContent);


    }


    public void deleteComment(Comment comment) {
        // 게시글의 상태를 DELETE로 변경
        comment.markAsDeleted();
    }
}
