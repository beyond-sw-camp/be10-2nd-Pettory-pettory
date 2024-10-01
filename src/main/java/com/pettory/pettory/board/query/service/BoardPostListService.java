package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardPostDTO;
import com.pettory.pettory.board.query.dto.BoardPostListResponse;
import com.pettory.pettory.board.query.mapper.BoardPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardPostListService {

    private final BoardPostMapper boardPostMapper;

    // 게시글 목록 조회
    @Transactional(readOnly = true)
    public BoardPostListResponse getPosts(String postTitle) {

        List<BoardPostDTO> posts = boardPostMapper.selectPosts(postTitle);

        int totalPosts = boardPostMapper.countPosts(postTitle);

        return BoardPostListResponse.builder()
                .postList(posts)
                .totalPosts(totalPosts)
                .build();
    }
}
