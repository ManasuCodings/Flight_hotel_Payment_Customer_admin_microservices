package com.authenticationService.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class JwtAuthResponse {
    private String token;
    private String userName;

}
