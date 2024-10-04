package com.pettory.pettory.family.query.mapper;

import com.pettory.pettory.family.query.dto.JoinedFamilyResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface FamilyMapper {

    JoinedFamilyResponse selectJoinedFamilyInfo(@Param("userEmail") String userEmail);
}
