package com.gatway.otherService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;


}
