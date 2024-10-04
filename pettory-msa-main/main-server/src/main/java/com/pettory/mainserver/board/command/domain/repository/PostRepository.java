package com.pettory.mainserver.board.command.domain.repository;

import com.pettory.mainserver.board.command.domain.aggregate.Post;

public interface PostRepository {

    Post save(Post post);
}
