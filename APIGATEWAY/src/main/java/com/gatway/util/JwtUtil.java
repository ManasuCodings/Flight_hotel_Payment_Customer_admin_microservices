package com.gatway.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;


@Component
public class JwtUtil {

    private static final String secret ="MRnw5pxk70uN4qjkE0AFJKGZ4O8d85mxmEIpuItNzrzDsF26s3p/rka0DhkLuCnz+qVz4udCFnNibt094P81/PA==";

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token);
    }

    public Key getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractRoleFromToken(String token) {
        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
        return (String) claims.get("roles"); // Assuming "role" is the key for the role claim
    }

}
