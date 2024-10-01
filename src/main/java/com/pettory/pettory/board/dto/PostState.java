package com.pettory.pettory.board.dto;


public enum PostState {
    ACTIVE,  // active 상태 활성화 상태이며 기본 default 값
    BAN,  // ban    상태 글이 정지를 당한 상태이므로 조회가 되지 않아야함
    DELETE   // delete 상태 삭제 된 상태이므로 조회가 되지 않아아햠
}
