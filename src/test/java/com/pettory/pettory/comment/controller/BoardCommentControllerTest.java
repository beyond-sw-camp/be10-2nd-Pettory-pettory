package com.pettory.pettory.comment.controller;

import com.pettory.pettory.PettoryApplication;
import com.pettory.pettory.comment.entity.BoardCommentEntity;
import com.pettory.pettory.comment.service.BoardCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PettoryApplication.class)  // 애플리케이션 설정 클래스 명시
@AutoConfigureMockMvc  // MockMvc 자동 설정
public class BoardCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardCommentService boardCommentService;

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateComment() throws Exception {
        String content = "This is a test comment";
        int postNum = 1;

        BoardCommentEntity commentEntity = new BoardCommentEntity();
        commentEntity.setCommentContent(content);

        when(boardCommentService.createComment(anyInt(), anyString())).thenReturn(commentEntity);

        mockMvc.perform(post("/comments/create")
                        .param("postNum", String.valueOf(postNum))
                        .param("content", content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentContent").value(content));

        verify(boardCommentService, times(1)).createComment(anyInt(), anyString());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateComment() throws Exception {
        String newContent = "Updated comment content";
        int commentNum = 1;

        BoardCommentEntity updatedComment = new BoardCommentEntity();
        updatedComment.setCommentContent(newContent);

        // Mock 서비스 동작 정의
        when(boardCommentService.updateComment(anyInt(), anyString())).thenReturn(updatedComment);

        // HTTP PUT 요청을 시뮬레이션하여 댓글 수정
        mockMvc.perform(put("/comments/update/{commentNum}", commentNum)
                        .param("content", newContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentContent").value(newContent));

        // 서비스 메서드 호출 검증
        verify(boardCommentService, times(1)).updateComment(anyInt(), anyString());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteComment() throws Exception {
        int commentNum = 1;

        // Mock 서비스 동작 정의
        doNothing().when(boardCommentService).deleteComment(commentNum);

        // HTTP DELETE 요청을 시뮬레이션
        mockMvc.perform(delete("/comments/delete/{commentNum}", commentNum))
                .andExpect(status().isNoContent());

        // 서비스 메서드 호출 검증
        verify(boardCommentService, times(1)).deleteComment(anyInt());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateSubComment() throws Exception {
        String content = "This is a sub-comment";
        int parentCommentNum = 1;

        BoardCommentEntity subComment = new BoardCommentEntity();
        subComment.setCommentContent(content);

        // Mock 서비스 동작 정의
        when(boardCommentService.createSubComment(anyInt(), anyString())).thenReturn(subComment);

        // HTTP POST 요청을 시뮬레이션하여 대댓글 작성
        mockMvc.perform(post("/comments/reply")
                        .param("parentCommentNum", String.valueOf(parentCommentNum))
                        .param("content", content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentContent").value(content));

        // 서비스 메서드 호출 검증
        verify(boardCommentService, times(1)).createSubComment(anyInt(), anyString());
    }
}
