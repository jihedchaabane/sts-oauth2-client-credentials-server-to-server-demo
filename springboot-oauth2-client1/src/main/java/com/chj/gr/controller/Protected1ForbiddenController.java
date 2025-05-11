package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
@PreAuthorize("hasAuthority('SCOPE_client1.somescope')")
public class Protected1ForbiddenController {

	/**
	 * Provide a wrong scope.
	 */
	@GetMapping("/forbidden")
    public String protectedEndpoint() {
        return "Hello from SECURED client1 ..";
    }
}