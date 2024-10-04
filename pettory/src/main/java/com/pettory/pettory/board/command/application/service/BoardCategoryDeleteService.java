package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.domain.aggregate.Category;
import com.pettory.pettory.board.command.domain.service.BoardCategoryDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPACategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCategoryDeleteService {

    private final BoardCategoryDomainService boardCategoryDomainService;
    private final JPACategoryRepository jpacategoryRepository;

    // 카테고리 삭제
    @Transactional
    public void deleteCategory(int categoryNum) {
        // 1. 카테고리를 조회
        Category category = jpacategoryRepository.findById((long)categoryNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        // 2. 도메인 서비스에 카테고리 삭제 요청
        boardCategoryDomainService.deleteCategory(category);
    }

}
