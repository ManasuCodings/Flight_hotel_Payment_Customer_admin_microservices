package com.authenticationService.feignServices;


import com.authenticationService.otherService.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/user/email/{email}")
    Optional<User> getUserByEmail(@PathVariable("email") String email);

    @GetMapping("/user/allusers")
    List<User> getAllUsers();

    @PostMapping("/user/createUser")
    User createOneUser(@RequestBody User user);
}
