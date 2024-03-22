package com.gatway.filter;

import com.gatway.otherService.UserClient;
import com.gatway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouteValidator routeValidator;

    private UserClient userClient;

    AuthenticationFilter(UserClient userClient){
        this.userClient=userClient;
    }

    @Autowired
    private JwtUtil jwtUtil;
    AuthenticationFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {



        return ((exchange, chain) -> {
            ServerHttpRequest request=null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing Authorization Header");

                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String token=null;
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);

                }

            try {

//                System.out.println(jwtUtil.extractRoleFromToken(token));
                //this is for mutating the token and adding username in httpserverrequest so other service will know this is the user
                //who is communicating
//                String email = jwtUtil.getUserNameFromToken(token);
//                System.out.println(email);
//                User user = userClient.getUserByEmail(email);
//                String role = user.getRole();
                request = exchange.getRequest().mutate().header("loggedInUser", jwtUtil.getUserNameFromToken(token))
                        .build();
                System.out.println(token);
                jwtUtil.validateToken(token);

            }catch (Exception e){

                logger.error("Error validating JWT token: {} ",e.getMessage());
                e.printStackTrace();
                throw new ClassCastException("Unauthorized token");
            }
            }

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static  class Config{

    }
}
