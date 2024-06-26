package com.authenticationService.security;

import com.authenticationService.otherService.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private User user;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //getToken
        String requestToken = request.getHeader("Authorization");

        //Bearer
        System.out.println(requestToken);

        String token=null;
        String username=null;
        //generate token
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUserNameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("not able to get JwtToken");
            }catch (ExpiredJwtException e){
                System.out.println("JwtToken has Expired");
            }catch (MalformedJwtException e){
                System.out.println("invalid JwtToken");
            }



        }
        else {
            System.out.println("token does not starts with Bearer ");

        }

        //once we get the token now we validate it
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {

                System.out.println("invalid Jwt token");
            }
        }else {

            System.out.println("username is null or context is not null");
        }

        filterChain.doFilter(request,response);
    }


}
