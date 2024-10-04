package com.pettory.mainserver.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postNum;
    private String postTitle;
    private String postContent;
    private int postCategoryNum;
    private int postHits = 0;
    private LocalDateTime postInsertDatetime;
    private LocalDateTime postUpdateDatetime;
    private LocalDateTime postDeleteDateTime;
    private int postWriterNum;
    @Enumerated(EnumType.STRING)
    private PostState postState;

    @ManyToOne
    @JoinColumn(name = "category_num")  // Category 테이블의 FK
    private Category category;



    // PostRequest를 통해 필요한 값들로 설정하는 생성자
    public Post(String postTitle, String postContent, int postCategoryNum, int postWriterNum) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postCategoryNum = postCategoryNum;
        this.postWriterNum = postWriterNum;
        this.postHits = 0;  // 기본적으로 조회수는 0
        this.postInsertDatetime = LocalDateTime.now();  // 현재 시간 설정
        this.postUpdateDatetime = null;  // 기본적으로 null
        this.postDeleteDateTime = null;  // 기본적으로 null
        this.postState = PostState.ACTIVE;  // 기본값으로 ACTIVE 설정


    }


    // 수정 메소드
    public void updatePost(String newTitle, String newContent) {
        this.postTitle = newTitle;
        this.postContent = newContent;
        this.postUpdateDatetime = LocalDateTime.now(); // 업데이트 시간 반영
    }

    // 삭제 메소드
    public void markAsDeleted() {
        this.postState = PostState.DELETE;
        this.postDeleteDateTime = LocalDateTime.now();  // 현재 시간을 삭제 시간으로 설정
    }
}
