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

//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthService authService
//	){
//		return args -> {
////			RegisteredRequest admin = RegisteredRequest
////					.builder()
////					.firstName("admin")
////					.lastName("admin")
////					.email("Admin@gmail.com")
////					.passWord("password")
////					.role(Role.ADMIN)
////					.build();
////			AuthenticationResponse register = authService.register(admin);
////			System.out.println("Access Token : " + register.getAccessToken());
////			RegisteredRequest manage = RegisteredRequest
////					.builder()
////					.firstName("manage")
////					.lastName("manage")
////					.email("manage@gmail.com")
////					.passWord("password")
////					.role(Role.MANAGER)
////					.build();
////			AuthenticationResponse registerMANAGER = authService.register(manage);
////			System.out.println("Access Token : " + registerMANAGER.getAccessToken());
//			RegisteredRequest user = RegisteredRequest
//					.builder()
//					.firstName("USER")
//					.lastName("USER")
//					.email("USER@gmail.com")
//					.passWord("password")
//					.role(Role.USER)
//					.build();
//			AuthenticationResponse register = authService.register(user);
//			System.out.println("USER Access Token : " + register.getAccessToken());
//
//		};
//	}

}
