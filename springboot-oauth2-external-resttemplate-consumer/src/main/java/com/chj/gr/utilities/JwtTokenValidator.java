package com.chj.gr.utilities;

import java.time.Instant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtTokenValidator {
    public static boolean isActive(String jwtToken) {
        try {
            // Parser le JWT sans vérifier la signature (uniquement pour lire l'expiration)
            Jws<Claims> claims = Jwts.parser()
                    .build()
                    .parseSignedClaims(jwtToken);

            // Récupérer la date d'expiration
            long expiration = claims.getPayload().getExpiration().getTime() / 1000; // En secondes
            long now = Instant.now().getEpochSecond();

            // Vérifier si le token est expiré
            return ! (now >= expiration);
        } catch (Exception e) {
            // En cas d'erreur (par exemple, JWT malformé), considérer comme expiré
            return false;
        }
    }
}
