package com.example.socialize.tokenConfig;

import com.example.socialize.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtTokenUtil {
    public static final String signingKey = "sjfkjskfkjskfjkskfjejierqkkkkrtn";
    public static final SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes());
    public static final Long expirationTime = 3600000L;

    @Autowired
    UserRepository userRepository;
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token,String username){

        return (checkUsername(username) && !isTokenExpired(token));
    }

    public static boolean isTokenExpired(String token){
        Date expiration= Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
    public boolean checkUsername(String username){
        return userRepository.existsByUsername(username).booleanValue();
    }
}
