package com.pettory.mainserver.board.query.service;

import com.pettory.mainserver.board.query.dto.BoardPostDTO;
import com.pettory.mainserver.board.query.dto.BoardPostListResponse;
import com.pettory.mainserver.board.query.mapper.BoardPostMapper;
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
