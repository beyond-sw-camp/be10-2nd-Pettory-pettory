package com.pettory.mainserver.board.command.application.service;


import com.pettory.mainserver.board.command.application.dto.PostRequest;
import com.pettory.mainserver.board.command.domain.aggregate.Post;
import com.pettory.mainserver.board.command.domain.service.BoardPostDomainService;
import com.pettory.mainserver.board.command.infrastructure.repository.JPAPostRepository;
import com.pettory.mainserver.board.query.dto.BoardPostDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardPostInsertService {

    private final BoardPostDomainService boardPostDomainService;
    private final JPAPostRepository jpaPostRepository;

    // 글 작성
    @Transactional
    public int createPost(PostRequest postRequest) {

        // Post 도메인 실행 후 entity 반환
        Post newPost = boardPostDomainService.createPost(postRequest);

        // save 로직 실행
        Post savedPost = jpaPostRepository.save(newPost);

        // 등록된 번호 반환
        return savedPost.getPostNum();
    }

    // 게시글 세부 정보를 가져오는 메서드 추가
    public List<BoardPostDetailDTO> getPostDetails(int postNum) {
        return jpaPostRepository.findPostDetailsByPostNum(postNum);
    }





}
