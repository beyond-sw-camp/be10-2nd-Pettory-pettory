package com.pettory.pettory.counseling.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.pettory.pettory.counseling.question.query.mapper", annotationClass = Mapper.class)
public class MybatisConfig {
}