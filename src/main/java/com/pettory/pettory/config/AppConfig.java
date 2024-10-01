package com.pettory.pettory.config;

import com.siot.IamportRestClient.IamportClient;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private String apiKey = "6235826201823656";
    private String secretKey = "pfZveAxxtAGooI79H9j1RbtKyysAGxEAlmtzYoej5UcFz2gikm0yFYs4sTQ5swM5DI4ebTvBhe5xsJXf";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
