package com.example.AuthenticationServer.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTUtility {
    @Value("${jwt.secret.key}")
    private String secretKey;
    private static final Long EXPIRATION_TIME = (long)(1000*60*60) * 24;

    private Key key;

    private Key getSigningKey(){
        if(key == null){
            key = Keys.hmacShaKeyFor(secretKey.getBytes());
        }
//        System.out.println(key);
        return key;
    }

    public String generateToken(String email){
//        JWT = Header + PayLoad + Secret Key....
         return Jwts
                 .builder()
                 .setSubject(email)
                 .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                 .signWith(getSigningKey())
                 .compact();

    }

    public String extractUserEmail(String token){
        return
                Jwts
                        .parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
    }

    public boolean isTokenExpired(String token){
        return
                Jwts
                        .parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getExpiration()
                        .before(new Date());
    }

    public boolean validateToken(String token){
       try{
             Jwts
                     .parserBuilder()
                     .setSigningKey(getSigningKey())
                     .build()
                     .parseClaimsJws(token)
                     .getBody();
             return true;
       }
       catch (Exception e){
             return false;
       }
    }

}
