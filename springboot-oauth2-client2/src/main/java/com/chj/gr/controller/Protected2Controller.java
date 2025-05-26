package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springboot-oauth2-client2/protected")
public class Protected2Controller {

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('SCOPE_client2.read')")
    public String hello() {
    	
        return "Hello from SECURED [sts-springboot-oauth2-client2]";
    }
    
}
