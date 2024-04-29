package com.airgear.search.security;

import com.airgear.entity.AuthToken;
import com.airgear.model.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider implements Serializable {

    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;


    public AuthToken generateToken(CustomUserDetails userDetails) {
        log.info("authentication.getName : {}", userDetails.getUsername());
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return new AuthToken(
                Jwts.builder()
                        .setSubject(userDetails.getUsername())
                        .claim(AUTHORITIES_KEY, authorities)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                        .compact()
        );
    }
}