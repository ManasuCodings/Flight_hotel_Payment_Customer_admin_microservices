package com.authenticationService.feignServices;

import com.authenticationService.dto.Admin;
import com.authenticationService.otherService.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "ADMINISTRATOR-SERVICE")
public interface AdministratorClient {


    @PostMapping("/admin/addAdmin")
    Admin createOneAdmin(Admin admin);

    @GetMapping("/admin/email/{email}")
    Optional<User> getAdminByEmail(@PathVariable("email") String email);
}
