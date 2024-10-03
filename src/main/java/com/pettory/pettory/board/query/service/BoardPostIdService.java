package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.command.infrastructure.repository.JPAPostRepository;
import com.pettory.pettory.board.query.dto.BoardPostDetailDTO;
import com.pettory.pettory.board.query.dto.BoardPostDetailResponse;
import com.pettory.pettory.board.query.mapper.BoardPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardPostIdService {

    private final BoardPostMapper boardPostMapper;
    private final JPAPostRepository jpaPostRepository;
    // 게시글 내용 조회
    @Transactional
    public BoardPostDetailResponse getPostsById(int id){

        List<BoardPostDetailDTO> detailposts = boardPostMapper.selectPostById(id);
//        List<String> fileLinks = boardPostMapper.postFilelinks(id);


        jpaPostRepository.incrementPostHits((long)id);
        return BoardPostDetailResponse.builder()
                .postDetail(detailposts)
//                .fileLinks(fileLinks)
                .build();



    }


}
