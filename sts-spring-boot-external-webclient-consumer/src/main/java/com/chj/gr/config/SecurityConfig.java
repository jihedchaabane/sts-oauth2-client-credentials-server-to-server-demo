package com.chj.gr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
        		.antMatchers("/public/**").permitAll()
        		.antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll();
        return http.build();
    }
}