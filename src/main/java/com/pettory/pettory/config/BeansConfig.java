package com.pettory.pettory.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

//    @Bean
//    ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        /* setter 메소드 미사용 시 ModelMapper가 private 필드에 접근 가능하도록 하는 설정 */
//        modelMapper.getConfiguration()
//                .setFieldAccessLevel(
//                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
//                )
//                .setFieldMatchingEnabled(true)
//                .setMatchingStrategy(MatchingStrategies.STRICT);
//        return modelMapper;
//    }
}
