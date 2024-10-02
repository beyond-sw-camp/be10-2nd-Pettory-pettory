package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="chatting_file_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chattingFileUniqueNum;
    private int fileChattingUniqueNum;
    private String chattingFileStorageDirectory;
    private int chattingFileSize;
}
