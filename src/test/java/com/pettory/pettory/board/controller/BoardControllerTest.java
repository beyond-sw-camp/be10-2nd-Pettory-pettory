package com.pettory.pettory.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettory.pettory.board.dto.BoardDTO;
import com.pettory.pettory.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)  // @WebMvcTest만 사용하고 @SpringBootTest는 제거
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testGetAllBoards() throws Exception {
        List<BoardDTO> boardDTOs = List.of(new BoardDTO());
        when(boardService.getAllBoards()).thenReturn(boardDTOs);

        mockMvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(boardService, times(1)).getAllBoards();
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testGetBoardById() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        when(boardService.getBoardById(1)).thenReturn(boardDTO);

        mockMvc.perform(get("/board/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postNum").exists());

        verify(boardService, times(1)).getBoardById(1);
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testCreateBoard() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setPostTitle("Test Title");
        boardDTO.setPostContent("Test Content");

        MockMultipartFile boardPart = new MockMultipartFile(
                "board",
                "",
                "application/json",
                new ObjectMapper().writeValueAsString(boardDTO).getBytes()
        );

        MockMultipartFile filePart = new MockMultipartFile("file", "", "application/octet-stream", new byte[0]);

        when(boardService.createPostWithFiles(any(BoardDTO.class), any(MultipartFile.class))).thenReturn(boardDTO);

        mockMvc.perform(multipart("/board/createpost")
                        .file(boardPart)
                        .file(filePart)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))  // CSRF 토큰 추가
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postTitle").value("Test Title"))
                .andExpect(jsonPath("$.postContent").value("Test Content"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testUpdateBoard() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setPostTitle("Updated Title");
        boardDTO.setPostContent("Updated Content");

        when(boardService.updateBoard(eq(1), any(BoardDTO.class))).thenReturn(boardDTO);

        mockMvc.perform(put("/board/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardDTO))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))  // CSRF 토큰 추가
                .andExpect(status().isOk());

        verify(boardService, times(1)).updateBoard(eq(1), any(BoardDTO.class));
    }


    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testDeleteBoard() throws Exception {
        mockMvc.perform(delete("/board/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))  // CSRF 토큰 추가
                .andExpect(status().isNoContent());

        verify(boardService, times(1)).deleteBoard(1);
    }

}
