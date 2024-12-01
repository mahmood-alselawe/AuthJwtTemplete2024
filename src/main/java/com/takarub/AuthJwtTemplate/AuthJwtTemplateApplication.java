package com.takarub.AuthJwtTemplate;

import com.takarub.AuthJwtTemplate.dto.AuthenticationResponse;
import com.takarub.AuthJwtTemplate.dto.RegisteredRequest;
import com.takarub.AuthJwtTemplate.model.Role;
import com.takarub.AuthJwtTemplate.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class AuthJwtTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthJwtTemplateApplication.class, args);
	}



}
