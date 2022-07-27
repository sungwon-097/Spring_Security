package com.cos.security1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // Spring Security Filter 가 Spring Filter Chain 에 등록됨
public class SecurityConfig { // WebSecurityConfigureAdapter 가 Deprecated. @Bean 등록으로 SecurityConfig 구현 가능


}
