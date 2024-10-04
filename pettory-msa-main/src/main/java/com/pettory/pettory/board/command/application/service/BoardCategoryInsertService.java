package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.application.dto.CategoryRequest;
import com.pettory.pettory.board.command.domain.aggregate.Category;
import com.pettory.pettory.board.command.domain.service.BoardCategoryDomainService;
import com.pettory.pettory.board.command.domain.service.BoardCommentDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPACategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCategoryInsertService {

    private final BoardCategoryDomainService boardCategoryDomainService;
    private final JPACategoryRepository jpaCategoryRepository;
    private final BoardCommentDomainService boardCommentDomainService;

    // 카테고리 작성
    @Transactional
    public int createCategory(CategoryRequest categoryRequest){

        // Category 도메인 실행 후 entity 반환
        Category newCategory = boardCategoryDomainService.createCategory(categoryRequest);

        // save 로직 실행
        Category savedCategory = jpaCategoryRepository.save(newCategory);

        // 등록된 번호 반환
        return savedCategory.getCategoryNum();
    }


}
