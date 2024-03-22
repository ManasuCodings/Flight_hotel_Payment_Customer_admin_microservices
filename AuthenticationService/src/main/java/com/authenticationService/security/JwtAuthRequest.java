package com.authenticationService.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class JwtAuthRequest {

    private String email;

    private String password;
}
