package io.lucasprojects.granna.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.lucasprojects.granna.domain.model.User;

@Component
public class JwtUtils {

    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt-expiration-milliseg}")
    private Long jwtExpirationMilliseg;

    public String generateToken(Authentication authentication) {
        Date expirationDate = new Date(new Date().getTime() + jwtExpirationMilliseg);
        User user = (User) authentication.getPrincipal();

        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));

            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(secretKey)
                    .compact();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private Claims getClaims(String token) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);

        if(claims == null)
            return null;

        return claims.getSubject();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if(claims == null)
            return false;

        String email = getUserName(token);
        Date expirationDate = claims.getExpiration();

        Date now = new Date(System.currentTimeMillis());

        if(email != null && now.before(expirationDate))
            return true;
        
        return false;
    }
}
