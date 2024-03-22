package com.authenticationService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY= 5*60*60;

//    private String generateSafeToken() {
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = new byte[64]; // 64 bytes * 8 = 512 bits, a little bit more than
//        //  512 required bits
//        random.nextBytes(bytes);
//        String base64 = Base64.getEncoder().encodeToString(bytes);
//        return base64;
//    }
    private static final String secret="MRnw5pxk70uN4qjkE0AFJKGZ4O8d85mxmEIpuItNzrzDsF26s3p/rka0DhkLuCnz+qVz4udCFnNibt094P81/PA==";


    public String getUserNameFromToken(String token){

        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToke(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        final Date expiration=getExpirationDateFromToke(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims= new HashMap<>();
        //modified code for settings role in claim of payload on token
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String subject){

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).
                signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        System.out.println(secret);
        final String username= getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }


}
