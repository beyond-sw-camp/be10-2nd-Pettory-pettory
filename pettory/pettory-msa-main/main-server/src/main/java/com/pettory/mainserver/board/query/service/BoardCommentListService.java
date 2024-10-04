package com.pettory.mainserver.board.query.service;

import com.pettory.mainserver.board.query.dto.BoardCommentDTO;
import com.pettory.mainserver.board.query.dto.BoardCommentResponse;
import com.pettory.mainserver.board.query.mapper.BoardCommentMapper;
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
