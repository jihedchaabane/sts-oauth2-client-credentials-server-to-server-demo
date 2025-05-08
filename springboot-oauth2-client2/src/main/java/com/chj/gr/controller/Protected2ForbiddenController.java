package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class Protected2ForbiddenController {

	@GetMapping("/forbidden")
	@PreAuthorize("hasAuthority('SCOPE_somescope')")
    public String protectedEndpoint() {
    	
        return "Hello from SECURED client2 ..";
    }
    
}
