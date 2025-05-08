package com.chj.gr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class Protected1ForbiddenController {

	/**
	 * Provide a wrong scope.
	 */
	@GetMapping("/forbidden")
	@PreAuthorize("hasAuthority('SCOPE_somescope')")
    public String protectedEndpoint(@RequestHeader("Authorization") String token) {
        return "This is a protected API. Token received: " + token;
    }
}