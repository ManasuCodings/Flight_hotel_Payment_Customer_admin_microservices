package com.authenticationService.controller;


import com.authenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentuser")
    public String  getCurrentUser(Principal principal){
        return principal.getName();
    }
}
