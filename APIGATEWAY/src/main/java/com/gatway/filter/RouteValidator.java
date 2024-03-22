package com.gatway.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

//import java.util.List;
import java.util.function.Predicate;
import java.util.*;
@Component
public class RouteValidator {

    public static final List<String> listOfByPassUrl = List.of(
            "/auth/login","/home/user","/v3/api-docs","/v2/api-docs",
            "/swagger-resources/**","/swagger-ui/**","/webjars/**"
    );

    public Predicate<ServerHttpRequest> isSecured = request-> listOfByPassUrl.stream().noneMatch(
            uri->request.
            getURI().getPath().contains(uri)
    );

}
