package com.pettory.pettory.pet.query.mapper;

import com.pettory.pettory.pet.query.dto.PetDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetMapper {

    List<PetDTO> findByUserId(@Param("userId") Long userId);

    List<PetDTO> findPetsByUserId(@Param("userId") Long userId);
}
