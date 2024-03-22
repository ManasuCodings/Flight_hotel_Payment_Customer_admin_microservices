package com.authenticationService.controller;

import com.authenticationService.dto.Admin;
import com.authenticationService.feignServices.AdministratorClient;
import com.authenticationService.feignServices.UserClient;
import com.authenticationService.otherService.User;
import com.authenticationService.security.JwtAuthRequest;
import com.authenticationService.security.JwtAuthResponse;
import com.authenticationService.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserClient userClient;

    @Autowired
    private AdministratorClient administratorClient;

//    @Autowired
//    private Service serviceImpl;

    @PostMapping("/userRegister")
    public ResponseEntity<User> registerCustomer(@RequestBody User user){
        User oneUser = userClient.createOneUser(user);
        return new ResponseEntity<>(oneUser, HttpStatus.CREATED);
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){
        Admin oneAdmin = administratorClient.createOneAdmin(admin);
        return new ResponseEntity<>(oneAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) {
        this.doAuthenticate(jwtAuthRequest.getEmail(), jwtAuthRequest.getPassword());
        System.out.println("+++++++++++++++++++++++++++++++++");
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getEmail());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        System.out.println("_______________________________________________");

        JwtAuthResponse jwtAuthResponse = JwtAuthResponse.builder().
                token(token)
                .userName(userDetails.getUsername())
                .build();
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new  UsernamePasswordAuthenticationToken(email,password);

        try{
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e){
            throw  new RuntimeException("invalid user");
        }

    }




}
