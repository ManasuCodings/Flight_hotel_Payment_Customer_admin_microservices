package com.administratorService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "AdminService-Application",version = "1.0",description = "This service manages ADMIN related information"))
public class AdministratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministratorServiceApplication.class, args);
	}

}
