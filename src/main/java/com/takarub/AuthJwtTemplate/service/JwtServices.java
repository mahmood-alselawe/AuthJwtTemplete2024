package com.takarub.AuthJwtTemplate.service;

import com.takarub.AuthJwtTemplate.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServices {

    private final static String SECRET_HEY = "8150e2bf70a8b62105a46ffedb868e94a8aa389110e6ba5c64148c1b32ef162d";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken,Claims::getSubject);

    }
    public <T> T extractClaim(String token, Function<Claims , T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_HEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }




    public String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*24*60 ))
                .signWith(getSignInKey())
                .compact();
    }


    // to check if token Is valid or not
    public boolean isValid(String token, UserDetails user) {
        String emailFromToken = extractUsername(token);
        // in first condition using token that came from request
        // extract from them the emailFromToken and compare it with email UserDetails
        // the second check if token is Expired or not
        return (emailFromToken.equals(user.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public Date extractIssuedAt(String jwtToken) {
        return extractClaim(jwtToken, Claims::getIssuedAt);
    }
}
