package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.application.dto.CategoryUpdateRequest;
import com.pettory.pettory.board.command.domain.aggregate.Category;
import com.pettory.pettory.board.command.domain.service.BoardCategoryDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPACategoryRepository;
import com.pettory.pettory.board.command.infrastructure.repository.JPACommentRepository;
import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCategoryUpdateService {

    private final BoardCategoryDomainService boardCategoryDomainService;
    private JPACategoryRepository jpacategoryRepository;

    // 카테고리 수정
    @Transactional
    public void updateCategory(int categoryNum, CategoryUpdateRequest categoryUpdateRequest) {

        // 1. 기존 카테고리 조회
        Category category = jpacategoryRepository.findById((long) categoryNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));


        // 2. 도메인 서비스로 댓글 수정 로직 위임
        boardCategoryDomainService.updateCategory(category, categoryUpdateRequest.getCategoryTitle());

        // 3. 변경된 게시글 저장
        jpacategoryRepository.save(category);
    }
}
