package com.pettory.userserver.family.query.mapper;

import com.pettory.userserver.family.query.dto.JoinedFamilyResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface FamilyMapper {

    JoinedFamilyResponse selectJoinedFamilyInfo(@Param("userEmail") String userEmail);
}
