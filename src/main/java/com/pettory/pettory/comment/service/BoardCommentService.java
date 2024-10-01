package com.pettory.pettory.comment.service;

import com.pettory.pettory.board.entity.BoardEntity;
import com.pettory.pettory.board.repository.BoardRepository;
import com.pettory.pettory.comment.entity.BoardCommentEntity;
import com.pettory.pettory.comment.repository.BoardCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardCommentService(BoardCommentRepository boardCommentRepository, BoardRepository boardRepository) {
        this.boardCommentRepository = boardCommentRepository;
        this.boardRepository = boardRepository;
    }

    // 댓글 작성
    public BoardCommentEntity createComment(int postNum, String content) {
        BoardEntity post = boardRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        BoardCommentEntity comment = new BoardCommentEntity();
        comment.setPost(post);
        comment.setCommentContent(content);
        comment.setCommentState("ACTIVE");
        comment.setPostInsertDatetime(LocalDateTime.now());

        return boardCommentRepository.save(comment);
    }

    // 댓글 수정
    public BoardCommentEntity updateComment(int commentNum, String newContent) {
        BoardCommentEntity comment = boardCommentRepository.findById(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        comment.setCommentContent(newContent);
        comment.setPostUpdateDatetime(LocalDateTime.now());

        return boardCommentRepository.save(comment);
    }

    // 댓글 삭제 (상태만 변경)
    public void deleteComment(int commentNum) {
        BoardCommentEntity comment = boardCommentRepository.findById(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        comment.setCommentState("DELETED");
        comment.setPostDeleteDatetime(LocalDateTime.now());
        boardCommentRepository.save(comment);
    }

    // 대댓글 작성
    public BoardCommentEntity createSubComment(int parentCommentNum, String content) {
        BoardCommentEntity parentComment = boardCommentRepository.findById(parentCommentNum)
                .orElseThrow(() -> new IllegalArgumentException("부모 댓글을 찾을 수 없습니다."));

        BoardCommentEntity subComment = new BoardCommentEntity();
        subComment.setPost(parentComment.getPost());
        subComment.setCommentContent(content);
        subComment.setRecommentNum(parentComment);
        subComment.setCommentState("ACTIVE");
        subComment.setPostInsertDatetime(LocalDateTime.now());

        return boardCommentRepository.save(subComment);
    }
}

