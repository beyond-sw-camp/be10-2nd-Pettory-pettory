package com.pettory.pettory.jointshopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // 리스너 사용을 위해 필요한 config
public class JpaConfig {
}
