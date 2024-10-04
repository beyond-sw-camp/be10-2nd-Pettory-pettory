package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardCommentDTO;
import com.pettory.pettory.board.query.dto.BoardCommentResponse;
import com.pettory.pettory.board.query.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentListService {

    private final BoardCommentMapper boardCommentMapper;

    // 댓글 조회
    public BoardCommentResponse getCommentList(int postNum) {


        List<BoardCommentDTO> comments = boardCommentMapper.selectComments(postNum);
        int totalComments = boardCommentMapper.countComments(postNum);

        return BoardCommentResponse.builder()
                .Comments(comments)
                .totalComments(totalComments)
                .build();

    }




}
