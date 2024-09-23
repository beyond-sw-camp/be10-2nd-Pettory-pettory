package com.pettory.pettory.jointshopping.query.mapper;

import com.pettory.pettory.jointshopping.query.dto.GroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<GroupDTO> selectGroups(@Param("offset") int offset, @Param("limit") int limit, @Param("categoryNum") Long categoryNum, @Param("groupName") String groupName);

    long countGroups(@Param("categoryNum") Long categoryNum, @Param("groupName") String groupName);

    GroupDTO selectGroupByNum(@Param("groupNum") Long groupNum);
}
