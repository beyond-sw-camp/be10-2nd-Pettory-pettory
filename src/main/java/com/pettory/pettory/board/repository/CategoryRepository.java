package com.pettory.pettory.board.repository;

import com.pettory.pettory.board.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
//    @Query(
////            value = "SELECT c FROM Category c ORDER BY c.categoryCode",
////            value = "SELECT category_num, category_title " +
////                    "FROM board_category " +
////                    "ORDER BY category_num",
////            nativeQuery = true  // JPQL일 경우 생략(default가 false)
//    )
@Query("SELECT c FROM CategoryEntity c ORDER BY c.CategoryNum")
    List<CategoryEntity> findAllCategory();
//    List<PostCategoryDTO> findAllCategory();
}
