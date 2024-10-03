package com.pettory.pettory.board.command.domain.repository;

import com.pettory.pettory.board.command.domain.aggregate.Post;

public interface PostRepository {

    Post save(Post post);
}
