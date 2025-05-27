package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sts-oauth2-client2-resource/protected")
public class Protected2ForbiddenController {

	@GetMapping("/forbidden")
	@PreAuthorize("hasAuthority('SCOPE_client2.somescope')")
    public String protectedEndpoint() {
    	
        return "Hello from SECURED [STS-OAUTH2-CLIENT2-RESOURCE]";
    }
    
}
