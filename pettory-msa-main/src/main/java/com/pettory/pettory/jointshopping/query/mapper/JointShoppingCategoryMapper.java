package com.pettory.pettory.jointshopping.query.mapper;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingCategoryDTO;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JointShoppingCategoryMapper {

    List<JointShoppingCategoryDTO> selectCategorys(@Param("categoryTitle") String categoryTitle);

    long countCategorys(@Param("categoryTitle") String categoryTitle);
}
