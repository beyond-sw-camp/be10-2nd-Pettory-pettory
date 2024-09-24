package com.pettory.pettory.jointshopping.query.mapper;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JointShoppingGroupMapper {
    List<JointShoppingGroupDTO> selectGroups(@Param("offset") int offset, @Param("limit") int limit, @Param("categoryNum") Long categoryNum, @Param("groupName") String groupName, @Param("products") String products);

    long countGroups(@Param("categoryNum") Long categoryNum, @Param("groupName") String groupName, @Param("products") String products);

    JointShoppingGroupDTO selectGroupByNum(@Param("groupNum") Long groupNum);
}
