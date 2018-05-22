package com.example.demo.security;

import com.example.demo.security.errors.TokenValidationErrorException;
import com.example.demo.security.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtProvider {
    private final String secretKey = "xcXc3kS82nsad23";
    private final int tokenExpirationTime = 30_000;

    public String generateToken(Authentication authentication) {
        CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
        
        Date currentDate = new Date();
        Date tokenExpirationDate = new Date(new Date().getTime() + tokenExpirationTime);
        
        return Jwts.builder()
                   .setSubject(currentUserDetails.getUsername())
                   .setIssuedAt(currentDate)
                   .setExpiration(tokenExpirationDate)
                   .signWith(SignatureAlgorithm.HS512, secretKey)
                   .compact();
    }
    
    public String getUsername(String token){
        Claims claims = Jwts.parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }
    
    public boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            throw new TokenValidationErrorException("Incorrect token");
        }
    }
}
