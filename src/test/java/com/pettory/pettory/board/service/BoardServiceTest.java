package com.pettory.pettory.board.service;

import com.pettory.pettory.board.dto.BoardDTO;
import com.pettory.pettory.board.entity.BoardEntity;
import com.pettory.pettory.board.entity.BoardFileEntity;
import com.pettory.pettory.board.mapper.boardMapper;
import com.pettory.pettory.board.repository.BoardFileRepository;
import com.pettory.pettory.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private boardMapper boardMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardFileRepository boardFileRepository;

    @Mock
    private MultipartFile multipartFile;

    @Test
    void testGetAllBoards() {
        List<BoardEntity> boardEntities = List.of(new BoardEntity());
        when(boardMapper.selectAllBoards()).thenReturn(boardEntities);

        List<BoardDTO> boardDTOs = boardService.getAllBoards();

        assertNotNull(boardDTOs);
        verify(boardMapper, times(1)).selectAllBoards();

        System.out.println("BoardDTOs: " + boardDTOs);
    }

    @Test
    void testGetBoardById() {
        // Given
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setPostHits(10); // 초기 조회수 설정


        when(boardRepository.findById(1)).thenReturn(Optional.of(boardEntity));
        when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);
        when(modelMapper.map(boardEntity, BoardDTO.class)).thenReturn(new BoardDTO());

        // When
        BoardDTO boardDTO = boardService.getBoardById(1);

        // Then
        assertNotNull(boardDTO);
        assertEquals(11, boardEntity.getPostHits()); // 조회수가 1 증가했는지 확인
        verify(boardRepository, times(1)).findById(1);
        verify(boardRepository, times(1)).save(boardEntity); // 조회수 증가 후 저장 확인
    }

    @Test
    void testCreateBoard() {
        BoardEntity boardEntity = new BoardEntity();
        BoardDTO boardDTO = new BoardDTO();
        when(modelMapper.map(boardDTO, BoardEntity.class)).thenReturn(boardEntity);
        when(boardRepository.save(boardEntity)).thenReturn(boardEntity);
        when(modelMapper.map(boardEntity, BoardDTO.class)).thenReturn(boardDTO);

        BoardDTO createdBoard = boardService.createBoard(boardDTO);

        assertNotNull(createdBoard);
        verify(boardRepository, times(1)).save(boardEntity);
    }

    @Test
    void testUpdateBoard() {
        BoardEntity boardEntity = new BoardEntity();
        BoardDTO boardDTO = new BoardDTO();
        when(boardRepository.findById(1)).thenReturn(Optional.of(boardEntity));
        doAnswer(invocation -> {
            BoardDTO source = invocation.getArgument(0);  // 첫 번째 인자: boardDTO
            BoardEntity target = invocation.getArgument(1); // 두 번째 인자: boardEntity
            // 매핑 시 동작하는 방식처럼 필드 업데이트를 수행 (예: 제목, 내용 등)
//            target.setPostTitle(source.getPostTitle());
//            target.setPostContent(source.getPostContent());
            return target;
        }).when(modelMapper).map(boardDTO, boardEntity);
        when(boardRepository.save(boardEntity)).thenReturn(boardEntity);
        when(modelMapper.map(boardEntity, BoardDTO.class)).thenReturn(boardDTO);

        BoardDTO updatedBoard = boardService.updateBoard(1, boardDTO);

        assertNotNull(updatedBoard);
        verify(boardRepository, times(1)).findById(1);
        verify(boardRepository, times(1)).save(boardEntity);
    }

    @Test
    void testDeleteBoard() {
        boardService.deleteBoard(1);
        verify(boardRepository, times(1)).deleteById(1);
    }

    @Test
    void testCreatePostWithFile() throws IOException {
        // Given
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setPostTitle("Test Post");
        boardDTO.setPostContent("This is a test post");

        BoardEntity boardEntity = new BoardEntity();
        when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);

        // Mock file upload
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getSize()).thenReturn(1024L);

        // When
        boardService.createPostWithFiles(boardDTO, multipartFile);

        // Then
        verify(boardRepository, times(1)).save(any(BoardEntity.class));
        verify(boardFileRepository, times(1)).save(any(BoardFileEntity.class));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }
}
