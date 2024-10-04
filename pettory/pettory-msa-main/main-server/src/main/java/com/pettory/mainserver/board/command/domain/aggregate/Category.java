package com.pettory.mainserver.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryNum;
    private String categoryTitle;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    // 새로운 카테고리 생성자
    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    // 업데이트 하기
    public void updateCategory(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    // 카테고리에 속한 게시글이 있는지 확인하는 메서드
    public boolean hasPosts() {
        return !posts.isEmpty();
    }
}
