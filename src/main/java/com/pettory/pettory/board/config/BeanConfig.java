package com.pettory.pettory.board.config;

import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.pettory.pettory.board.mapper")
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /* setter 메소드 미사용 시 ModelMapper가 private 필드에 접근 가능하도록 하는 설정 */
        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
                )
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
