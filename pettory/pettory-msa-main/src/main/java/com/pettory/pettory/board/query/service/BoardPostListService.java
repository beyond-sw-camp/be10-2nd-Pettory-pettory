package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardPostDTO;
import com.pettory.pettory.board.query.dto.BoardPostListResponse;
import com.pettory.pettory.board.query.mapper.BoardPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardPostListService {

    private final BoardPostMapper boardPostMapper;

    // 게시글 목록 조회
    public BoardPostListResponse getPostList() {


        List<BoardPostDTO> posts = boardPostMapper.selectPosts();
        int totalPosts = boardPostMapper.countPosts();

        return BoardPostListResponse.builder()
                .postList(posts)
                .totalPosts(totalPosts)
                .build();
    }
}
