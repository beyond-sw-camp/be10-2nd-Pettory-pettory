package com.pettory.pettory.chat.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 2024.09.25 황희순 chatBeanConfig 생성 - ModelMapper 를 사용하기 위함. */
@Configuration
public class ChatBeanConfig {

    @Bean
    public ModelMapper modelmapper() {
        ModelMapper modelMapper = new ModelMapper();
        /* setter 메소드 미사용시 ModelMapper 가 private 필드에 접근 가능하도록 설정 */
        modelMapper.getConfiguration().setFieldAccessLevel(
                org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        ).setFieldMatchingEnabled(true);
        return modelMapper;
    }

}
