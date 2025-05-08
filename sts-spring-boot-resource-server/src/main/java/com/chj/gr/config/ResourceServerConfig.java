package com.chj.gr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                
                .mvcMatchers("/api/public/**").permitAll()
                .mvcMatchers("/actuator/**").permitAll()
                .mvcMatchers("/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
//                .mvcMatchers("/products/**").access("hasAuthority('SCOPE_products.read')")
                /**
                 * Replaced by com.tb.config.AnnotationSecurityConfig.java
                   @EnableGlobalMethodSecurity(
						prePostEnabled = true	// enables @PreAuthorize and @PostAuathorize
						, securedEnabled = true // enables @Secured
						, jsr250Enabled = true	// enables @RolesAllowed (Ensure JSR-250 annotations are enabled)
                 */
                
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}