package com.flightService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "FlightService-Application",version = "1.0",description = "This service manages customer related  FLightBooking information"))
public class FlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightServiceApplication.class, args);
	}

}
