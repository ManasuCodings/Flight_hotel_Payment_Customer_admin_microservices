package com.gatway.otherService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

//
    @GetMapping("user/email/{email}")
    User getUserByEmail(@PathVariable("email") String email);

}
