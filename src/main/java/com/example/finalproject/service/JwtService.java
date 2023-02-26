package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
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

    private static final String SECRET_KEY="7336763979244226452948404D635166546A576D5A7134743777217A25432A46";
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extraAllClaim(token);
        return claimsResolver.apply(claims);
    }

    public String generatToken(MyUser user){
        return generatToken(new HashMap<>(),user);
    }

    public String generatToken(Map<String,Object> exClaim,  MyUser user){
        return Jwts
                .builder()
                .setClaims(exClaim)
                .setSubject(user.getEmail())
                .claim("role",user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Boolean tokenIsValid(String token , UserDetails userDetails){
        final String userName=extractUserName(token);
        return (userName.equals(userDetails.getUsername())) &&!isTokenEexpiret(token);

    }

    private Boolean isTokenEexpiret(String token) {
        return extractExpire(token).before(new Date());
    }

    private Date extractExpire(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extraAllClaim(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInkey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInkey() {
        byte [] keyBytes= Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
