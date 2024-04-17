package com.HotelMalik.reserv2.Security;

import com.HotelMalik.reserv2.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "d6f80511ac33be22d4c042eef194702b2970408b7accedd350991548f398c31e";

    private Claims extractAllClaims(String token){
        return  Jwts
                .parser()
                .verifyWith(getSignIn())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }


    public <T> T extractClaim(String token , Function<Claims ,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    public String extractUsername(String token){
        return  extractClaim(token , Claims ::getSubject);
    }
    public boolean isValid(String token , UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername()))&&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSignIn())
                .compact();
        return  token;
    }

    private SecretKey getSignIn() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
