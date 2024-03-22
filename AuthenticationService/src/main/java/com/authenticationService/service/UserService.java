package com.authenticationService.service;

import com.authenticationService.otherService.User;
import com.authenticationService.feignServices.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    public List<User> getAllUser() {

        List<User> allUsers = userClient.getAllUsers();
        return allUsers;
    }
}
