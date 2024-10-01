package com.pettory.pettory.comment.service;

import com.pettory.pettory.board.entity.BoardEntity;
import com.pettory.pettory.board.repository.BoardRepository;
import com.pettory.pettory.comment.entity.BoardCommentEntity;
import com.pettory.pettory.comment.repository.BoardCommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BoardCommentServiceTest {

    @Mock
    private BoardCommentRepository boardCommentRepository;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardCommentService boardCommentService;

    public BoardCommentServiceTest() {
        MockitoAnnotations.openMocks(this);  // Mockito 초기화
    }

    @Test
    public void testCreateComment() {
        int postNum = 1;
        String content = "This is a test comment";

        BoardEntity post = new BoardEntity();
        when(boardRepository.findById(postNum)).thenReturn(Optional.of(post));

        BoardCommentEntity comment = new BoardCommentEntity();
        comment.setCommentContent(content);
        comment.setPost(post);

        when(boardCommentRepository.save(any(BoardCommentEntity.class))).thenReturn(comment);

        BoardCommentEntity result = boardCommentService.createComment(postNum, content);

        assertNotNull(result);
        assertEquals(content, result.getCommentContent());
        verify(boardCommentRepository, times(1)).save(any(BoardCommentEntity.class));
    }

    @Test
    public void testUpdateComment() {
        int commentNum = 1;
        String newContent = "Updated comment content";

        BoardCommentEntity existingComment = new BoardCommentEntity();
        existingComment.setCommentContent("Old comment content");

        when(boardCommentRepository.findById(commentNum)).thenReturn(Optional.of(existingComment));
        when(boardCommentRepository.save(existingComment)).thenReturn(existingComment);

        BoardCommentEntity result = boardCommentService.updateComment(commentNum, newContent);

        assertNotNull(result);
        assertEquals(newContent, result.getCommentContent());
        verify(boardCommentRepository, times(1)).save(existingComment);
    }

    @Test
    public void testDeleteComment() {
        int commentNum = 1;

        BoardCommentEntity comment = new BoardCommentEntity();
        when(boardCommentRepository.findById(commentNum)).thenReturn(Optional.of(comment));

        boardCommentService.deleteComment(commentNum);

        verify(boardCommentRepository, times(1)).save(comment);
        assertEquals("DELETED", comment.getCommentState());
    }

    @Test
    public void testCreateSubComment() {
        int parentCommentNum = 1;
        String content = "This is a sub-comment";

        BoardCommentEntity parentComment = new BoardCommentEntity();
        when(boardCommentRepository.findById(parentCommentNum)).thenReturn(Optional.of(parentComment));

        BoardCommentEntity subComment = new BoardCommentEntity();
        subComment.setRecommentNum(parentComment);
        subComment.setCommentContent(content);

        when(boardCommentRepository.save(any(BoardCommentEntity.class))).thenReturn(subComment);

        BoardCommentEntity result = boardCommentService.createSubComment(parentCommentNum, content);

        assertNotNull(result);
        assertEquals(content, result.getCommentContent());
        assertEquals(parentComment, result.getRecommentNum());
        verify(boardCommentRepository, times(1)).save(any(BoardCommentEntity.class));
    }
}
