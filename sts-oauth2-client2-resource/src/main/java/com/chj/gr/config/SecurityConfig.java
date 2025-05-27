package com.chj.gr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http
	        .authorizeRequests()
	        .antMatchers("/actuator/**").permitAll()
	        .antMatchers("/sts-oauth2-client2-resource/public/**").permitAll()
	        .antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
	        
	//        .antMatchers("/api/secure/**").access("hasAuthority('SCOPE_client2.read')")
	        /**
	        @Configuration
	        @EnableGlobalMethodSecurity(
	        		prePostEnabled = true	// enables @preAuthorize and @PostAuathorize
	        		, securedEnabled = true // enables @secured
	        		, jsr250Enabled = true	// enables @RolesAllowed (Ensure JSR-250 annotations are enabled)
	        )
	        public class AnnotationSecurityConfig { }
	        */
	        
	        
	        .anyRequest().authenticated()
	    .and()
	    	.oauth2ResourceServer(oauth2 -> oauth2
	    		.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
	    	);
	
	return http.build();
	}

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
}

