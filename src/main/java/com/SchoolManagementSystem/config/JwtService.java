package com.SchoolManagementSystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "4nWWHDXbeJwCNBNH/xT32u4O8jYgsy71NgO00D1jxtXjnMA5HgWFDT0bFeCcHF6K/yNae7aN5PREfTba11SpZNt/Xb7kwWWYL/OW9Bww3TZCBmYoWRLD4tlF2h4227JP940gBBAy3+wawHFzsNJfKMBz8fCS443dZ+8lgmTZClCKLeebl9NzebthnnQ5Rn0WwGvJs0ZJwm56TaSqvrUfXauLIoS7zRrZ74MqVjq0PoO6BVisQJZ/bS8n2wCSXp01CVdzE3YxD4cKFzNLPOybCG40x6L+S5ASVc9Ps4b+Yx3staZTQSv8soGHf5BYYr8mJavqCUggdjeWkWAMi8zX18ZfG//ix6Dqtclt8NXhVPQ=";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //extracting one single claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpration(token).before(new Date());
    }

    private Date extractExpration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            //any information that i want to store within my token
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){


        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
