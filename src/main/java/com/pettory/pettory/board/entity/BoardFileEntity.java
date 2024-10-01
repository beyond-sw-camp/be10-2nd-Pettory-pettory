package com.pettory.pettory.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_file_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardFileUniqueNum;
    private String boardFileStorageDirectory;
    private int postNum;
    private int boardFileSize;

    public BoardFileEntity(String boardFileStorageDirectory, int postNum, int boardFileSize) {
        this.boardFileStorageDirectory = boardFileStorageDirectory;
        this.postNum = postNum;
        this.boardFileSize = boardFileSize;
    }
}

