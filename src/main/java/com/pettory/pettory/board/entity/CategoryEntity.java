package com.pettory.pettory.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_num")
    private int CategoryNum;

    @Column(name = "category_title")
    private String postCategoryTitle;
}
