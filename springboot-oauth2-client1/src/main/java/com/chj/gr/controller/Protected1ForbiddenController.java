package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springboot-oauth2-client1/protected")
public class Protected1ForbiddenController {

	/**
	 * Provide a wrong scope.
	 */
	@GetMapping("/forbidden")
	@PreAuthorize("hasAuthority('SCOPE_client1.somescope')")
    public String protectedEndpoint() {
        return "Hello from SEURED [sts-springboot-oauth2-client1]";
    }
}