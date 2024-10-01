package com.pettory.pettory.board.service;

import com.pettory.pettory.board.dto.BoardDTO;
import com.pettory.pettory.board.entity.BoardEntity;
import com.pettory.pettory.board.entity.BoardFileEntity;
import com.pettory.pettory.board.mapper.boardMapper;
import com.pettory.pettory.board.repository.BoardFileRepository;
import com.pettory.pettory.board.repository.BoardRepository;
import com.pettory.pettory.board.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final boardMapper boardMapper;
    private final BoardFileRepository boardFileRepository;

    public BoardService(BoardRepository boardRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, boardMapper boardMapper, BoardFileRepository boardFileRepository) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.boardMapper = boardMapper;
        this.boardFileRepository = boardFileRepository;
    }

    // 게시물 목록 조회
    public List<BoardDTO> getAllBoards() {
        List<BoardEntity> boardEntities = boardMapper.selectAllBoards();
        return boardEntities.stream()
                .map(boardEntity -> modelMapper.map(boardEntity, BoardDTO.class))
                .collect(Collectors.toList());
    }

    // 게시물 상세 조회
    public BoardDTO getBoardById(int id) {
        // 1. ID로 게시글을 조회
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

//         2. 조회수 증가
        boardEntity.setPostHits(boardEntity.getPostHits() + 1);

//         3. 변경된 조회수를 저장
        boardRepository.save(boardEntity);

        // 4. Entity를 DTO로 변환하여 반환
        return modelMapper.map(boardEntity, BoardDTO.class);
    }

    // 게시물 생성
    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        BoardEntity savedEntity = boardRepository.save(boardEntity);
        return modelMapper.map(savedEntity, BoardDTO.class);
    }

    // 게시물 수정
    @Transactional
    public BoardDTO updateBoard(int id, BoardDTO boardDTO) {
        return boardRepository.findById(id)
                .map(boardEntity -> {
                    modelMapper.map(boardDTO, boardEntity);
                    BoardEntity updatedEntity = boardRepository.save(boardEntity);
                    return modelMapper.map(updatedEntity, BoardDTO.class);
                }).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(int id){
        boardRepository.deleteById(id);
    }

    // 게시글 작성 및 파일 저장
    @Transactional
    public BoardDTO createPostWithFiles(BoardDTO boardDTO, MultipartFile file) throws IOException {
        // 1. 게시글 저장
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setPostTitle(boardDTO.getPostTitle());
        boardEntity.setPostContent(boardDTO.getPostContent());
        boardRepository.save(boardEntity);

        // 2. 파일 저장 처리
        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/";
            String originalFilename = file.getOriginalFilename();
            String storageFileName = System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + storageFileName;

            // 파일 저장 디렉토리에 파일 저장
            File dest = new File(filePath);
            file.transferTo(dest);

            // 파일 메타데이터 저장
            BoardFileEntity fileEntity = new BoardFileEntity(filePath, boardEntity.getPostNum(), (int) file.getSize());
            boardFileRepository.save(fileEntity);
        }
        return boardDTO;
    }



}
