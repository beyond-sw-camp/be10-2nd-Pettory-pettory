package com.pettory.pettory.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_num")
    private int categoryNum;

    @Column(name = "category_title")
    private String categoryTitle;

    public BoardCategoryEntity(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}

