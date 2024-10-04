package com.pettory.mainserver.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_file_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardFileUniqueNum;
    private String boardFIleStorageDirectory;
    private Long postNum;
    private Long boardFileSize;

    // 생성자 추가
    public FileEntity(String boardFIleStorageDirectory, Long postNum, Long boardFileSize) {
        this.boardFIleStorageDirectory = boardFIleStorageDirectory;
        this.postNum = postNum;
        this.boardFileSize = boardFileSize;
    }
}
