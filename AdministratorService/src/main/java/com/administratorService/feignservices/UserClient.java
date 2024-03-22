package com.administratorService.feignservices;

import com.administratorService.otherservice.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @PostMapping("/user/createUser")
    User addOneUser(@RequestBody User user);

    @DeleteMapping("/user/deleteuser/{userId}")
    Optional<String> deleteOneUser(@PathVariable("userId") long userId);
}
