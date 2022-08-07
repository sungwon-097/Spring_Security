package com.example.jwt_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 서버가 응답을 할 때 json 을 JS 에서 처리 할 수 있게 할지
        config.addAllowedOrigin("*"); // IP 응답 허용
        config.addAllowedHeader("*"); // header 응답 허용
        config.addAllowedMethod("*"); // CRUD 요청 허용

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
