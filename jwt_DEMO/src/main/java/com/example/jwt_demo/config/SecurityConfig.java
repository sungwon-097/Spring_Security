package com.example.jwt_demo.config;

import com.example.jwt_demo.filter.MyFilter1;
import com.example.jwt_demo.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final CorsFilter corsFilter;

    @Bean
    protected SecurityFilterChain configureSetting(HttpSecurity http) throws Exception{
        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // stateless 방식을 사용 할 것임!
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
        return http.build();
    }
}
