package com.pettory.pettory.board.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryRequest {

    private final String categoryTitle;
}
