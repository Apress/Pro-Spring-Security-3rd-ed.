package com.apress.JWT_Security_Authentication.security;


import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtilities{


    @Value("${jwt.jwtsecret}")
    private String jwtsecret;

    @Value("${jwt.jwtExpirationTime}")
    private Long jwtExpirationTime;



    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token) {return Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(token).getBody();}

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration); }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUsername(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email , List<String> roles) {

        return Jwts.builder().setSubject(email).claim("role",roles).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(jwtExpirationTime, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, jwtsecret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public String getToken (HttpServletRequest httpServletRequest) {
         final String bearerToken = httpServletRequest.getHeader("Authorization");
         if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
         {return bearerToken.substring(7,bearerToken.length()); } // The part after "Bearer "
         return null;
    }

}
