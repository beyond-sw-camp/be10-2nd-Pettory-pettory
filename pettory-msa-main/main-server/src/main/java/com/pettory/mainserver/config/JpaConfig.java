package com.pettory.mainserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // 엔터티가 생성, 수정되는 시간이 자동 기록되게 하기 위함
public class JpaConfig {
}
