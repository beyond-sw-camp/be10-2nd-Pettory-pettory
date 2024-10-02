package com.pettory.pettory.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// security requirement
import io.swagger.v3.oas.models.security.SecurityRequirement;
// security scheme
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(swaggerInfo())
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication")) // JWT 인증 추가
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    private Info swaggerInfo() {
        return new Info()
                .title("Pettory API Document")
                .description("SpringBoot Swagger 문서")
                .version("1.0.0");
    }
}
