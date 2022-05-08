package com.example.demo.service.service;

import com.example.demo.model.security.AuthenticatedUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenGenerator {

    private static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(final AuthenticatedUser authenticatedUser) {
        return Jwts.builder()
                .setSubject(authenticatedUser.getUsername())
                .signWith(TOKEN_KEY)
                .compact();
    }

    public String getUsernameFromToken(final String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(TOKEN_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}

