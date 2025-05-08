package com.chj.gr.model;

import java.time.Instant;

import com.chj.gr.exceptions.CustomException;

public class Token {
    private String accessToken;
    private long expiresAt;

    public Token(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        // Calculer l'heure d'expiration (maintenant + expiresIn)
        this.expiresAt = Instant.now().getEpochSecond() + expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isExpired() {
        // Comparer l'heure actuelle avec l'heure d'expiration
        return Instant.now().getEpochSecond() >= expiresAt;
    }
    
	public static boolean isActive(TokenResponse tokenResponse) {
		// Retourner un objet Token avec le token et l'heure d'expiration
		Token t = new Token(tokenResponse.getAccess_token(), tokenResponse.getExpires_in());
		if (!t.isExpired()) {
			return true;
		} else {
			throw new CustomException("Token is expired");
		}

	}
}
