package com.authenticationService.security;

import com.authenticationService.feignServices.AdministratorClient;
import com.authenticationService.feignServices.UserClient;
import com.authenticationService.otherService.User;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private AdministratorClient administratorClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        User admin = null;

        try {
            Optional<User> userOptional = userClient.getUserByEmail(username);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                System.out.println(user);
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (FeignException e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }

        if (user != null && "USER".equals(user.getRole())) {
            System.out.println("Inside if loop");
            //from here
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),this.getAuthorities(user.getRole()));
           // return user; to here
        } else {
            try {
                Optional<User> adminOptional = administratorClient.getAdminByEmail(username);
                if (adminOptional.isPresent()) {
                    admin = adminOptional.get();
                } else {
                    throw new UsernameNotFoundException("Admin not found");
                }
            } catch (FeignException e) {
                System.out.println("Error fetching admin: " + e.getMessage());
            }
            if (admin != null && "ADMIN".equals(admin.getRole())) {

                return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),this.getAuthorities(admin.getRole()));
             //modified code from here
               // return admin;
               // to here
            } else {
                throw new UsernameNotFoundException("User or admin not found");
            }
        }
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
