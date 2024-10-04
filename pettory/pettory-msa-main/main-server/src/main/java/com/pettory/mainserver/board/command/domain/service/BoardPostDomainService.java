package com.pettory.mainserver.board.command.domain.service;

import com.pettory.mainserver.board.command.application.dto.PostRequest;
import com.pettory.mainserver.board.command.domain.aggregate.Post;
import com.pettory.mainserver.board.command.infrastructure.repository.JPAPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardPostDomainService {

    private final JPAPostRepository postRepository;

    // PostRequest를 받아 새로운 Post 객체 생성
    public Post createPost(PostRequest postRequest) {
        return new Post(
                postRequest.getPostTitle(),
                postRequest.getPostContent(),
                postRequest.getPostCategoryNum(),
                postRequest.getPostWriterNum()
        );
    }

    // 도메인 객체를 저장
    public Post savePost(Post newPost) {
        return postRepository.save(newPost);
    }

    // 도메인 객체를 수정
    public void updatePost(Post post, String postTitle, String postContent) {
        // 1. 게시글 제목과 내용 변경을 위한 메서드 호출
        post.updatePost(postTitle, postContent);


    }

    // 도메인 객체를 삭제
    public void deletePost(Post post) {
        // 1. 게시글의 상태를 DELETE로 변경
        post.markAsDeleted();

    }
}
